package org.example.rfshop.Report.Application.CreateReportUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.Auth.Application.ExtractUserEmailFromSecurityContextUseCase.ExtractUserEmailFromSecurityContext;
import org.example.rfshop.Email.Application.SendEmailUseCase.SendEmailUseCase;
import org.example.rfshop.Email.Domain.EmailTypes.EmailReport;
import org.example.rfshop.Report.Domain.Dto.Request.CreateReportDto;
import org.example.rfshop.Report.Domain.Dto.Response.ReportResponseDto;
import org.example.rfshop.Report.Infrastructure.Mapper.ReportMapper;
import org.example.rfshop.Report.Infrastructure.Model.Report;
import org.example.rfshop.Report.Infrastructure.Repository.ReportRepository;
import org.example.rfshop.Review.Infrastructure.Model.Review;
import org.example.rfshop.Review.Infrastructure.Repository.ReviewRepository;
import org.example.rfshop.User.Application.GetUserByEmail.GetUserByEmail;
import org.example.rfshop.User.Infrastructure.Model.User;
import org.example.rfshop.Utils.Exception.DeniedAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.concurrent.CompletableFuture;

@Service
public class CreateReportUseCaseImpl implements CreateReportUseCase{

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;
    private final ReviewRepository reviewRepository;
    private final GetUserByEmail getUserByEmail;
    private final ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext;
    private final SendEmailUseCase sendEmailUseCase;

    @Autowired
    public CreateReportUseCaseImpl(ReportRepository reportRepository, ReportMapper reportMapper, ReviewRepository reviewRepository, GetUserByEmail getUserByEmail, ExtractUserEmailFromSecurityContext extractUserEmailFromSecurityContext, SendEmailUseCase sendEmailUseCase) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
        this.reviewRepository = reviewRepository;
        this.getUserByEmail = getUserByEmail;
        this.extractUserEmailFromSecurityContext = extractUserEmailFromSecurityContext;
        this.sendEmailUseCase = sendEmailUseCase;
    }

    @Override
    public ReportResponseDto execute(Long reviewId, CreateReportDto createReportDto) {
        Review review = this.reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review with id " + reviewId + " not found"));
        User currentUser = this.getUserByEmail.execute(this.extractUserEmailFromSecurityContext.execute(SecurityContextHolder.getContext()));

        if (!currentUser.getId().equals(review.getBarberShop().getOwner().getId())) {
            throw new DeniedAction("Only the owner of barber shop can report a review");
        }

        Report report = this.reportMapper.toEntity(createReportDto);
        report.setReview(review);
        this.reportRepository.save(report);

        CompletableFuture.runAsync(() -> { //run the method to send email in parallel (Reduced time to execution from 3.80s to 30ms)
            try {
                this.sendEmailUseCase.execute(new EmailReport("romanmeclazcke1234@gmail.com", "Report", review));
            } catch (MessagingException e) {
                throw new RuntimeException("Error sending email", e);
            }
        });

        return this.reportMapper.toDto(report);
    }
}

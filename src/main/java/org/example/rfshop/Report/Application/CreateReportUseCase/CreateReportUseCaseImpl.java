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
        try {
            Review review = this.reviewRepository.findById(reviewId)
                    .orElseThrow(() -> new EntityNotFoundException("Review with id " + reviewId + " not found"));
            User user = this.getUserByEmail.execute(this.extractUserEmailFromSecurityContext.execute(SecurityContextHolder.getContext()));

            if (!user.getId().equals(review.getBarberShop().getOwner().getId())) {
                throw new DeniedAction("Only the owner of barber shop can report a review");
            }

            Report report = this.reportMapper.toEntity(createReportDto);
            report.setReview(review);
            this.sendEmailUseCase.execute(new EmailReport("romanmeclazcke1234@gmail.com", "Report", review));
            return this.reportMapper.toDto(this.reportRepository.save(report));
        } catch (EntityNotFoundException | DeniedAction e) {
            // Manejo de excepciones específicas
            throw e;
        } catch (MessagingException e) {
            // Manejo de excepciones de correo electrónico
            throw new RuntimeException("Error sending email", e);
        } catch (Exception e) {
            // Manejo de cualquier otra excepción
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }
}

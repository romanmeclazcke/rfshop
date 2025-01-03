package org.example.rfshop.Email.Domain.EmailTypes;

import org.example.rfshop.Review.Infrastructure.Model.Review;
import java.text.SimpleDateFormat;

public class EmailReport extends Email {
    private final Review review;

    public EmailReport(String to, String subject, Review review) {
        super(to, subject);
        this.review = review;
    }

    @Override
    public String generateEmail() {
        String reviewer = review.getUser().getName();
        String barberShop = review.getBarberShop().getName();
        Long rating = review.getRating();
        String comment = review.getReview();
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(review.getCreatedAt());

        return String.format("""
            <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>Email Report</title>
                        <style>
                            body {
                                font-family: Arial, sans-serif;
                                line-height: 1.6;
                                color: #333;
                                margin: 0;
                                padding: 0;
                                background-color: #f9f9f9;
                            }
                            .email-container {
                                max-width: 600px;
                                margin: 20px auto;
                                background: #ffffff;
                                border: 1px solid #dddddd;
                                border-radius: 8px;
                                padding: 20px;
                            }
                            .header {
                                text-align: center;
                                border-bottom: 1px solid #dddddd;
                                padding-bottom: 10px;
                            }
                            .header h1 {
                                color: #FF5722;
                            }
                            .content {
                                margin: 20px 0;
                            }
                            .content h2 {
                                color: #555;
                            }
                            .review-details {
                                background: #f4f4f4;
                                padding: 10px;
                                border-radius: 5px;
                                border: 1px solid #ddd;
                            }
                            .footer {
                                text-align: center;
                                font-size: 0.9em;
                                color: #999;
                                margin-top: 20px;
                            }
                        </style>
                    </head>
                    <body>
                        <div class="email-container">
                            <div class="header">
                                <h1>Review Report Notification</h1>
                            </div>
                            <div class="content">
                                <h2>Dear Admin,</h2>
                                <p>A new report has been submitted for the following review. Please review the details below:</p>
                                <div class="review-details">
                                    <p><strong>Reviewer:</strong> %s</p>
                                    <p><strong>BarberShop:</strong> %s</p>
                                    <p><strong>Rating:</strong> %d/5</p>
                                    <p><strong>Comment:</strong> %s</p>
                                    <p><strong>Date:</strong> %s</p>
                                </div>
                            </div>
                            <div class="footer">
                                <p>&copy; 2025 RF Shop. All Rights Reserved.</p>
                            </div>
                        </div>
                    </body>
                    </html>
        """, reviewer, barberShop, rating, comment, date);
    }
}
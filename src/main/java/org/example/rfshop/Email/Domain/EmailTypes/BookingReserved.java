package org.example.rfshop.Email.Domain.EmailTypes;

import org.example.rfshop.Booking.Infrastructure.Model.Booking;

import java.text.SimpleDateFormat;

public class BookingReserved extends Email{
    private final Booking bookingReserved;

    public BookingReserved(String to, String subject,Booking bookingReserved) {
        super(to, subject);
        this.bookingReserved = bookingReserved;
    }

    @Override
    public String generateEmail() {
        String userName = bookingReserved.getUser().getName();
        String bookingStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(bookingReserved.getBookingStart());
        String bookingEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(bookingReserved.getBookingEnd());

        return String.format("""
            <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>Booking Confirmation</title>
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
                                <h1>Booking Confirmation</h1>
                            </div>
                            <div class="content">
                                <h2>Dear %s,</h2>
                                <p>Your booking has been successfully reserved. Please find the details below:</p>
                                <p><strong>Booking Start:</strong> %s</p>
                                <p><strong>Booking End:</strong> %s</p>
                            </div>
                            <div class="footer">
                                <p>&copy; 2025 RF Shop. All Rights Reserved.</p>
                            </div>
                        </div>
                    </body>
                    </html>
        """, userName, bookingStart, bookingEnd);
    }
}
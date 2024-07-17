<%-- 
    Document   : footer
    Created on : Mar 18, 2024, 11:47:15 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!DOCTYPE html>
<footer>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            
            .footer
            {
                margin:0;
                padding: 0;
                box-sizing: border-box;
                font-family: 'Roboto', sans-serif;
                
                height: 100vh;
                width: 100%;
                background: #171717;
                display: flex;
                align-items: center;
                justify-content: center;
                flex-direction: column;
            }
            
            .footer h2
            {
                color: white;
                font-weight: 500;
                font-size: 2rem;
                margin-bottom: 0rem;
                letter-spacing: 1px;
                word-spacing: 2px;
                margin-bottom: 0.7rem;
            }
            .footer p
            {
                font-size: 0.9rem;
                color: #d8d8d8;
            }
            .social_links
            {
                padding: 1rem;
                background: #383838;
                border-radius: 1rem;
                margin-top: 2rem;
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
            }
            .social_links .combo
            {
                transform: scale(0.9);
                transition: 0.4s ease-in;
                margin: 0 0.5rem;
                height: 3em;
                width: 3em;
                line-height: 3em;
            }
            .combo .circle
            {
                color: #f9f9f9;
                transition: 0.4s ease-in;
                font-size: 3em;
            }
            .combo .icon
            {
                color: #444343;
                font-size: 2rem;
                transform: translateY(1px);
                transition: 0.2s ease-in;
            }
            .combo:hover
            {
                transform: scale(1.1);
            }
            .combo:hover , .combo:hover .icon
            {
                color:white;
            }
            .ig:hover , .ig:hover .circle
            {
                color:#e91e63;
            }
            .fb:hover , .fb:hover .circle
            {
                color:#2196f3;
            }
            .yt:hover , .yt:hover .circle
            {
                color:#f44336;
            }
            .tw:hover , .tw:hover .circle
            {
                color:#2196f3;
            }
            .gt:hover , .gt:hover .circle{
                color: #1f1f1f;
            }

        </style>
    </head>
    <div class="footer" style="width: 100%;
                               height: 300px;" >
    
        <h2><img src="./images/LitJunction-logo.png" alt="LOGO" style="width: 400px;"></h2>
        <p style="display: flex;
                  justify-content: center;" >########################################</p>
        <div class="social_links">
            <a href="">
                <span class="fa-stack fa-lg ig combo">
                    <i class="fa fa-circle fa-stack-2x circle"></i>
                    <i class="fa fa-instagram fa-stack-1x fa-inverse icon"></i>
                </span>
            </a>
            <a href="">
                <span class="fa-stack fa-lg fb combo">
                    <i class="fa fa-circle fa-stack-2x circle"></i>
                    <i class="fa fa-facebook fa-stack-1x fa-inverse icon"></i>
                </span>
            </a>
            <a href="">
                <span class="fa-stack fa-lg yt combo">
                    <i class="fa fa-circle fa-stack-2x circle"></i>
                    <i class="fa fa-youtube-play fa-stack-1x fa-inverse icon"></i>
                </span>
            </a>
            <a href="">
                <span class="fa-stack fa-lg tw combo">
                    <i class="fa fa-circle fa-stack-2x circle"></i>
                    <i class="fa fa-twitter fa-stack-1x fa-inverse icon"></i>
                </span>
            </a>
            <a href="">
                <span class="fa-stack fa-lg gt combo">
                    <i class="fa fa-circle fa-stack-2x circle"></i>
                    <i class="fa fa-codepen fa-stack-1x fa-inverse icon"></i>
                </span>
            </a>
            <a href="">
                <span class="fa-stack fa-lg tw combo">
                    <i class="fa fa-circle fa-stack-2x circle"></i>
                    <i class="fa fa-linkedin fa-stack-1x fa-inverse icon"></i>
                </span>
            </a>
        </div>
        
    
    </div>
</footer>

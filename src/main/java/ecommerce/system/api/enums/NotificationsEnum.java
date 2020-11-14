package ecommerce.system.api.enums;

public enum NotificationsEnum {

    PASSWORD_RECOVER (
            1, "/auth/recuperar-senha/", true,
            "Recuperação de senha",
            "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"https://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html xmlns=\"https://www.w3.org/1999/xhtml\"><head><title>Recuperação de senha</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><meta name=\"viewport\" content=\"width=device-width\" /><style type=\"text/css\">@media screen{@font-face{font-family:'Source Sans Pro';font-style:normal;font-weight:400;src:local('Source Sans Pro Regular'), local('SourceSansPro-Regular'), url(https://fonts.gstatic.com/s/sourcesanspro/v10/ODelI1aHBYDBqgeIAH2zlBM0YzuT7MdOe03otPbuUS0.woff) format('woff')}@font-face{font-family:'Source Sans Pro';font-style:normal;font-weight:700;src:local('Source Sans Pro Bold'), local('SourceSansPro-Bold'), url(https://fonts.gstatic.com/s/sourcesanspro/v10/toadOcfmlt9b38dHJxOBGFkQc6VGVFSmCnC_l7QZG60.woff) format('woff')}}body,table,td,a{-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%}table,td{mso-table-rspace:0pt;mso-table-lspace:0pt}img{-ms-interpolation-mode:bicubic}a[x-apple-data-detectors]{font-family:inherit !important;font-size:inherit !important;font-weight:inherit !important;line-height:inherit !important;color:inherit !important;text-decoration:none !important}div[style*=\"margin: 16px 0;\"]{margin:0 !important}body{width:100% !important;height:100% !important;padding:0 !important;margin:0 !important}table{border-collapse:collapse !important}a{color:#1a82e2}img{height:auto;line-height:100%;text-decoration:none;border:0;outline:none}p{margin:0}</style></head><body style=\"background-color: #e9ecef;\"><div style=\"display: none; max-width: 0; max-height: 0; overflow: hidden; font-size: 1px; line-height: 1px; color: #fff; opacity: 0;\"> Esqueceu sua senha? Não se preocupe, te ajudamos com isso!</div><table class=\"body\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" data-made-with-foundation><tbody><tr><td class=\"float-center\" align=\"center\" valign=\"top\"><center><table class=\"container\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tbody><tr align=\"center\" bgcolor=\"#e9ecef\"><td><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\"><tr><td align=\"center\" valign=\"top\" style=\"padding: 36px 24px;\"></td></tr></table></td></tr><tr><td align=\"center\" bgcolor=\"#e9ecef\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\"><tr><td style=\"padding: 36px 24px 0; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; border-top: 3px solid #d4dadf; background-color: #ffffff;\"><h1 style=\"margin: 0; font-size: 32px; font-weight: 700; letter-spacing: -1px; line-height: 48px;\"> Recuperação de senha</h1></td></tr></table></td></tr><tr><td align=\"center\" bgcolor=\"#e9ecef\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\"><tr><td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px;\"><p> Pressione o botão abaixo para cadastrar uma nova senha.</p></td></tr><tr><td align=\"left\" bgcolor=\"#ffffff\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td align=\"center\" bgcolor=\"#ffffff\" style=\"padding: 12px;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td align=\"center\" bgcolor=\"#1a82e2\" style=\"border-radius: 6px;\"> <a href=\"[[link]]\" target=\"_blank\" style=\"display: inline-block; padding: 16px 36px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; color: #ffffff; text-decoration: none; border-radius: 6px;\"> Cadastrar nova senha </a></td></tr></table></td></tr></table></td></tr><tr><td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 24px;font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif;font-size: 16px;line-height: 24px;border-bottom: 3px solid #d4dadf;\"><p> Conte conosco, <br> Seus amigos do E-commerce System</p></td></tr></table></td></tr><tr><td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 24px;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\"><tr><td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 12px 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; color: #666;\"><p> Você recebeu esse e-mail porque recebemos uma solitação de recuperação de senha para a sua conta. <br> Se não foi você, por favor, ignore este e-mail ou entre em contato conosco para mais detalhes.</p></td></tr><tr><td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 12px 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; color: #666;\"><p> Avenida Paulista, 123, São Paulo - SP <br> contato@ecommercesystem.com</p></td></tr></table></td></tr></tbody></table></center></td></tr></tbody></table></body></html>",
            null
    ),

    EMAIL_VERIFY (
            2, "/auth/verificar-email/", true,
            "Verificação de email",
            "",
            null
    ),

    STORE_ADMIN (
            3, "/auth/admin-loja", true,
            "Convite para administrar loja",
            "",
            null
    ),

    STOCK_ALERT (
            4, "/auth/login", false,
            "Alerta de estoque",
            "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><title>Alerta de estoque</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"><meta name=\"viewport\" content=\"width=device-width\"></head><body style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;background-color:#e9ecef;height:100%!important;margin:0!important;padding:0!important;width:100%!important\"><style type=\"text/css\">@media screen{@font-face{font-family:'Source Sans Pro';font-style:normal;font-weight:400;src:local('Source Sans Pro Regular'),local('SourceSansPro-Regular'),url(https://fonts.gstatic.com/s/sourcesanspro/v10/ODelI1aHBYDBqgeIAH2zlBM0YzuT7MdOe03otPbuUS0.woff) format('woff')}@font-face{font-family:'Source Sans Pro';font-style:normal;font-weight:700;src:local('Source Sans Pro Bold'),local('SourceSansPro-Bold'),url(https://fonts.gstatic.com/s/sourcesanspro/v10/toadOcfmlt9b38dHJxOBGFkQc6VGVFSmCnC_l7QZG60.woff) format('woff')}}</style><div style=\"display:none;max-width:0;max-height:0;overflow:hidden;font-size:1px;line-height:1px;color:#fff;opacity:0\">Um dos seus produtos esgotou!</div><table class=\"body\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" data-made-with-foundation=\"\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;border-collapse:collapse!important;mso-table-lspace:0;mso-table-rspace:0\"><tbody><tr><td class=\"float-center\" align=\"center\" valign=\"top\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;mso-table-lspace:0;mso-table-rspace:0\"><center><table class=\"container\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;border-collapse:collapse!important;mso-table-lspace:0;mso-table-rspace:0\"><tbody><tr align=\"center\" bgcolor=\"#e9ecef\"><td style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;mso-table-lspace:0;mso-table-rspace:0\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;border-collapse:collapse!important;max-width:600px;mso-table-lspace:0;mso-table-rspace:0\"><tbody><tr><td align=\"center\" valign=\"top\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;mso-table-lspace:0;mso-table-rspace:0;padding:36px 24px\"></td></tr></tbody></table></td></tr><tr><td align=\"center\" bgcolor=\"#e9ecef\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;mso-table-lspace:0;mso-table-rspace:0\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;border-collapse:collapse!important;max-width:600px;mso-table-lspace:0;mso-table-rspace:0\"><tbody><tr><td style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;background-color:#fff;border-top:3px solid #d4dadf;font-family:'Source Sans Pro',Helvetica,Arial,sans-serif;mso-table-lspace:0;mso-table-rspace:0;padding:36px 24px 0\"><h1 style=\"margin:0;font-size:32px;font-weight:700;letter-spacing:-1px;line-height:48px\">Alerta de estoque</h1></td></tr></tbody></table></td></tr><tr><td align=\"center\" bgcolor=\"#e9ecef\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;mso-table-lspace:0;mso-table-rspace:0\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;border-collapse:collapse!important;max-width:600px;mso-table-lspace:0;mso-table-rspace:0\"><tbody><tr><td align=\"left\" bgcolor=\"#ffffff\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;font-family:'Source Sans Pro',Helvetica,Arial,sans-serif;font-size:16px;line-height:24px;mso-table-lspace:0;mso-table-rspace:0;padding:24px\"><p style=\"margin:0\">Seu produto [[productName]] esgotou!<br>Acesse o sistema no botão abaixo:</p></td></tr><tr><td align=\"left\" bgcolor=\"#ffffff\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;mso-table-lspace:0;mso-table-rspace:0\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;border-collapse:collapse!important;mso-table-lspace:0;mso-table-rspace:0\"><tbody><tr><td align=\"center\" bgcolor=\"#ffffff\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;mso-table-lspace:0;mso-table-rspace:0;padding:12px\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;border-collapse:collapse!important;mso-table-lspace:0;mso-table-rspace:0\"><tbody><tr><td align=\"center\" bgcolor=\"#1a82e2\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;border-radius:6px;mso-table-lspace:0;mso-table-rspace:0\"><a href=\"[[link]]\" target=\"_blank\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;border-radius:6px;color:#fff;display:inline-block;font-family:'Source Sans Pro',Helvetica,Arial,sans-serif;font-size:16px;padding:16px 36px;text-decoration:none\">Acessar o sistema</a></td></tr></tbody></table></td></tr></tbody></table></td></tr><tr><td align=\"left\" bgcolor=\"#ffffff\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;border-bottom:3px solid #d4dadf;font-family:'Source Sans Pro',Helvetica,Arial,sans-serif;font-size:16px;line-height:24px;mso-table-lspace:0;mso-table-rspace:0;padding:24px\"><p style=\"margin:0\">Conte conosco,<br>Seus amigos do E-commerce System</p></td></tr></tbody></table></td></tr><tr><td align=\"center\" bgcolor=\"#e9ecef\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;mso-table-lspace:0;mso-table-rspace:0;padding:24px\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;border-collapse:collapse!important;max-width:600px;mso-table-lspace:0;mso-table-rspace:0\"><tbody><tr><td align=\"center\" bgcolor=\"#e9ecef\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;color:#666;font-family:'Source Sans Pro',Helvetica,Arial,sans-serif;font-size:14px;line-height:20px;mso-table-lspace:0;mso-table-rspace:0;padding:12px 24px\"><p style=\"margin:0\">Você recebeu essa mensagem porque este e-mail está cadastrado numa conta de administrador da [[storeName]].<br><br>Se você não reconhece esta mensagem, por favor, ignore este e-mail e entre em contato conosco para mais detalhes.</p></td></tr><tr><td align=\"center\" bgcolor=\"#e9ecef\" style=\"-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;color:#666;font-family:'Source Sans Pro',Helvetica,Arial,sans-serif;font-size:14px;line-height:20px;mso-table-lspace:0;mso-table-rspace:0;padding:12px 24px\"><p style=\"margin:0\">Avenida Paulista, 123, São Paulo - SP<br>contato@ecommercesystem.com</p></td></tr></tbody></table></td></tr></tbody></table></center></td></tr></tbody></table></body></html>",
            new String[]{"[[productName]]", "[[storeName]]"}
    ),

    ORDER_ALERT (
            5, "/auth/login", false,
            "Atualização sobre o seu pedido",
            "",
            null
    );

    private final int id;
    private final String route;
    private final boolean tokenNeeded;
    private final String subject;
    private final String messageTemplate;
    private final String[] templateVariables;

    NotificationsEnum(int id, String route, boolean tokenNeeded, String subject, String messageTemplate, String[] templateVariables) {
        this.id = id;
        this.route = route;
        this.tokenNeeded = tokenNeeded;
        this.subject = subject;
        this.messageTemplate = messageTemplate;
        this.templateVariables = templateVariables;
    }

    public int getId() {
        return id;
    }

    public String getRoute() {
        return route;
    }

    public boolean isTokenNeeded() {
        return tokenNeeded;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }

    public String[] getTemplateVariables() {
        return templateVariables;
    }
}

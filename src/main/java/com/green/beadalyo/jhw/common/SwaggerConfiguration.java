package com.green.beadalyo.jhw.common;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "배달요",
                description = "Second Project",
                version = "v1"
        ),
        security = @SecurityRequirement(name = "authorization")
)
@SecurityScheme(
        type = SecuritySchemeType.HTTP
        , name = "authorization"
        , in = SecuritySchemeIn.HEADER
        , bearerFormat = "JWT"
        , scheme = "Bearer"
)
public class SwaggerConfiguration {}
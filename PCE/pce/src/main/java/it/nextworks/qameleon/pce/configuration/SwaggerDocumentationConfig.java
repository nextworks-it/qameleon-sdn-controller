package it.nextworks.qameleon.pce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")

@Configuration
public class SwaggerDocumentationConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("tapi-topology,tapi-notification,tapi-common,tapi-path-computation API")
            .description("          This module contains TAPI Topology Model definitions.          Source: TapiTopology.uml          Copyright (c) 2018 Open Networking Foundation (ONF). All rights reserved.          License: This module is distributed under the Apache License 2.0          - The TAPI YANG models included in this TAPI release are a *normative* part of the TAPI SDK.          - The YANG specifications have been generated from the corresponding UML model using the [ONF EAGLE UML2YANG mapping tool]          <https://github.com/OpenNetworkingFoundation/EagleUmlYang>          and further edited manually to comply with the [ONF IISOMI UML2YANG mapping guidelines]          <https://wiki.opennetworking.org/display/OIMT/UML+-+YANG+Guidelines>          - Status of YANG model artifacts can be determined by referring to the corresponding UML artifacts.          As described in the UML models, some artifacts are considered *experimental*, and thus the corresponding YANG artifacts.          - The ONF TAPI release process does not guarantee backward compatibility of YANG models across major versions of TAPI releases.          The YANG model backward compatibility criteria are outlined in section 11 of <https://tools.ietf.org/html/rfc7950>.          YANG models included in this release may not be backward compatible with previous TAPI releases.,          This module contains TAPI Notification Model definitions.          Source: TapiNotification.uml          Copyright (c) 2018 Open Networking Foundation (ONF). All rights reserved.          License: This module is distributed under the Apache License 2.0          - The TAPI YANG models included in this TAPI release are a *normative* part of the TAPI SDK.          - The YANG specifications have been generated from the corresponding UML model using the [ONF EAGLE UML2YANG mapping tool]          <https://github.com/OpenNetworkingFoundation/EagleUmlYang>          and further edited manually to comply with the [ONF IISOMI UML2YANG mapping guidelines]          <https://wiki.opennetworking.org/display/OIMT/UML+-+YANG+Guidelines>          - Status of YANG model artifacts can be determined by referring to the corresponding UML artifacts.          As described in the UML models, some artifacts are considered *experimental*, and thus the corresponding YANG artifacts.          - The ONF TAPI release process does not guarantee backward compatibility of YANG models across major versions of TAPI releases.          The YANG model backward compatibility criteria are outlined in section 11 of <https://tools.ietf.org/html/rfc7950>.          YANG models included in this release may not be backward compatible with previous TAPI releases.,          This module contains TAPI Common Model definitions.          Source: TapiCommon.uml          Copyright (c) 2018 Open Networking Foundation (ONF). All rights reserved.          License: This module is distributed under the Apache License 2.0          - The TAPI YANG models included in this TAPI release are a *normative* part of the TAPI SDK.          - The YANG specifications have been generated from the corresponding UML model using the [ONF EAGLE UML2YANG mapping tool]          <https://github.com/OpenNetworkingFoundation/EagleUmlYang>          and further edited manually to comply with the [ONF IISOMI UML2YANG mapping guidelines]          <https://wiki.opennetworking.org/display/OIMT/UML+-+YANG+Guidelines>          - Status of YANG model artifacts can be determined by referring to the corresponding UML artifacts.          As described in the UML models, some artifacts are considered *experimental*, and thus the corresponding YANG artifacts.          - The ONF TAPI release process does not guarantee backward compatibility of YANG models across major versions of TAPI releases.          The YANG model backward compatibility criteria are outlined in section 11 of <https://tools.ietf.org/html/rfc7950>.          YANG models included in this release may not be backward compatible with previous TAPI releases.,          This module contains TAPI Path Computation Model definitions.          Source: TapiPathComputation.uml          Copyright (c) 2018 Open Networking Foundation (ONF). All rights reserved.          License: This module is distributed under the Apache License 2.0          - The TAPI YANG models included in this TAPI release are a *normative* part of the TAPI SDK.          - The YANG specifications have been generated from the corresponding UML model using the [ONF EAGLE UML2YANG mapping tool]          <https://github.com/OpenNetworkingFoundation/EagleUmlYang>          and further edited manually to comply with the [ONF IISOMI UML2YANG mapping guidelines]          <https://wiki.opennetworking.org/display/OIMT/UML+-+YANG+Guidelines>          - Status of YANG model artifacts can be determined by referring to the corresponding UML artifacts.          As described in the UML models, some artifacts are considered *experimental*, and thus the corresponding YANG artifacts.          - The ONF TAPI release process does not guarantee backward compatibility of YANG models across major versions of TAPI releases.          The YANG model backward compatibility criteria are outlined in section 11 of <https://tools.ietf.org/html/rfc7950>.          YANG models included in this release may not be backward compatible with previous TAPI releases.")
            .license("")
            .licenseUrl("http://unlicense.org")
            .termsOfServiceUrl("")
            .version("2.1.3")
            .contact(new Contact("","", ""))
            .build();
    }

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("io.swagger.api"))
                    .build()
                .directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }

}

package com.thierry.webservices.restfulwebservices.controllers.versioning;

import com.thierry.webservices.restfulwebservices.models.Name;
import com.thierry.webservices.restfulwebservices.models.PersonV1;
import com.thierry.webservices.restfulwebservices.models.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class VersioningUsersController {

    // URL
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson(){
        return new PersonV1("Lionel Messi");
    }

    // URL
    @GetMapping("/v2/person")
    public PersonV2 getLatestVersionOfPerson(){
        return new PersonV2(new Name("Lionel", "Messi"));
    }

    // RequestParam
    @GetMapping(path="/person", params = "version=1")
    public  PersonV1 getFirstVersionOfPersonWithParams(){
        return new PersonV1("Lionel Messi");
    }

    // Header
    @GetMapping(path="/person/header", headers = "X-API-VERSION=2")
    public  PersonV2 getFirstVersionOfPersonWithHeader(){
        return new PersonV2(new Name("Lionel", "Messi"));
    }

    @GetMapping(path="/person/accept", produces = "application/vnd.company.app-v2+json")
    public  PersonV2 getFirstVersionOfPersonWithMediaType(){
        return new PersonV2(new Name("Lionel", "Messi"));
    }
}

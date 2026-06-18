package org.example.tareasapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "api-externa", url = "https://jsonplaceholder.typicode.com")
public interface ApiExternaClient {

    @GetMapping("/posts")
    List<PostExterno> obtenerPosts();
}
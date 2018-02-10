package br.com.miguelmf.robots.api.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/mars")
public class RobotController {

    // http://localhost:8080/rest/mars/MMRMMRMM

    @RequestMapping(value = "/{instructions}", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<?> home(@PathVariable("instructions") String instructions) {
        return ResponseEntity.status(200).body(new Response(200, "(1, 2, N)"));
    }


    class Response {
        private Integer code;
        private String message;

        public Response() {
        }

        public Response(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}

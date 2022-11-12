package com.Code.Controller.Authentication;

import com.Code.Entity.Auth.Account;
import com.Code.Entity.PT.personalTrainer;
import com.Code.Entity.User.user;
import com.Code.Model.Authentication.jwtDecodeModel;
import com.Code.Model.Authentication.jwtRequest;
import com.Code.Model.Response.PTResponse;
import com.Code.Model.Response.userInfoResponse;
import com.Code.Security.JwtTokenUtil;
import com.Code.Service.Auth.AccountService;
import com.Code.Service.PT.personalTrainerService;
import com.Code.Service.User.userService;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RequestMapping(value = "/auth")
@RestController
public class loginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private userService userService;

    @Autowired
    private personalTrainerService personaTrainerService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @SneakyThrows
    @PostMapping(value = "/login")
    public String createToken(@RequestBody jwtRequest jwtRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorect....", e);
        }
        final UserDetails userDetails = userDetailService.loadUserByUsername(jwtRequest.getUsername());
        return jwtTokenUtil.generateToken(userDetails);
    }


    @GetMapping(value = "/getUserInfo")
    public userInfoResponse getUserInfo(@RequestParam("jwt") String jwt) {
        jwtDecodeModel jwtDecodeModel = extractModel(jwt);
        Account accountResult = accountService.findByUsername(jwtDecodeModel.sub);
        user user = userService.findByUserName(accountResult.getUsername());
        return new userInfoResponse(user);
    }

    @GetMapping(value = "/getPTInfo")
    public PTResponse getPTInfo(@RequestParam("jwt") String jwt) {
        jwtDecodeModel jwtDecodeModel = extractModel(jwt);
        Account accountResult = accountService.findByUsername(jwtDecodeModel.sub);
        personalTrainer pt = personaTrainerService.findByUsername(accountResult.getUsername());
        return new PTResponse(pt);
    }

    public jwtDecodeModel extractModel(String jwt){
        String[] pieces = jwt.split("\\.");
        String b64payload = pieces[1];
        String jsonString = null;
        try {
            jsonString = new String(Base64.decodeBase64(b64payload), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return new Gson().fromJson(jsonString, jwtDecodeModel.class);
    }
}

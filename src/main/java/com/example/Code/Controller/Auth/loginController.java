package com.example.Code.Controller.Auth;

import com.example.Code.Entity.Auth.Account;
import com.example.Code.Entity.PT.personal_trainer;
import com.example.Code.Entity.User.user;
import com.example.Code.Model.*;
import com.example.Code.Security.JwtTokenUtil;
import com.example.Code.Service.Auth.AccountService;
import com.example.Code.Service.PT.personal_trainerService;
import com.example.Code.Service.User.userService;
import com.google.gson.Gson;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

@RequestMapping(value = "/auth")
@RestController
public class loginController {
    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private AccountService accountService ;

    @Autowired
    private userService userService;

    @Autowired
    private personal_trainerService personal_trainerService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/login")
    public String createToken(@RequestBody jwtRequest jwtRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        }
        catch (BadCredentialsException e){
            throw new Exception("Incorect....",e);
        }
        final UserDetails userDetails = userDetailService.loadUserByUsername(
                jwtRequest.getUsername()
        );
        return jwtTokenUtil.generateToken(userDetails);
    }


    @GetMapping(value = "/getUserInfo")
    public userInfoResponse getUserInfo(@RequestParam("jwt") String jwt){
        //Properties prop = new Properties();
        String[] pieces = jwt.split("\\.");
        String b64payload = pieces[1];
        String jsonString = null;
        try {
            jsonString = new String(Base64.decodeBase64(b64payload), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        jwtDecodeModel jwtDecodeModel = new Gson().fromJson(jsonString, jwtDecodeModel.class);
        Account accountResult = accountService.findByUsername(jwtDecodeModel.sub);
        user user =  userService.findByUserName(accountResult.getUsername());
        return new userInfoResponse(user);
    }

    @GetMapping(value = "/getPTInfo")
    public PTResponseModel getPTInfo(@RequestParam("jwt") String jwt){
        //Properties prop = new Properties();
        String[] pieces = jwt.split("\\.");
        String b64payload = pieces[1];
        String jsonString = null;
        try {
            jsonString = new String(Base64.decodeBase64(b64payload), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        jwtDecodeModel jwtDecodeModel = new Gson().fromJson(jsonString, jwtDecodeModel.class);
        Account accountResult = accountService.findByUsername(jwtDecodeModel.sub);
        personal_trainer pt =  personal_trainerService.findByUsername(accountResult.getUsername());

        return new PTResponseModel(pt);
    }
}


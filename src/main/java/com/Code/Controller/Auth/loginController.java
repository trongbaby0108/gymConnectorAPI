package com.Code.Controller.Auth;

import com.Code.Entity.Auth.Account;
import com.Code.Entity.PT.personalTrainer;
import com.Code.Entity.User.user;
import com.Code.Model.PTResponseModel;
import com.Code.Model.jwtDecodeModel;
import com.Code.Model.jwtRequest;
import com.Code.Model.userInfoResponse;
import com.Code.Service.Auth.AccountService;
import com.Code.Security.JwtTokenUtil;
import com.example.Code.Service.PT.personal_trainerService;
import com.google.gson.Gson;
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
    private AuthenticationManager authenticationManager ;

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private AccountService accountService ;

    @Autowired
    private com.Code.Service.User.userService userService;

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
        personalTrainer pt =  personal_trainerService.findByUsername(accountResult.getUsername());

        return new PTResponseModel(pt);
    }
}


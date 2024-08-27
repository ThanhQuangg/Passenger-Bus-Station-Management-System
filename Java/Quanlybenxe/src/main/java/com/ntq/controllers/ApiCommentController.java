package com.ntq.controllers;

import com.ntq.pojo.Comment;
import com.ntq.pojo.Company;
import com.ntq.pojo.Bus;
import com.ntq.pojo.User;
import com.ntq.services.BusService;
import com.ntq.services.UserService;
import com.ntq.services.CompanyService;
import com.ntq.services.CommentService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiCommentController {
    @Autowired
    private  CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    
    @GetMapping("/comments")
    @CrossOrigin
    public ResponseEntity<List<Comment>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.commentService.getComment(params), HttpStatus.OK);
    }

    @GetMapping(path = "/comments/{commentID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> retrieve(@PathVariable(value = "commentID") int commentID) {
        return new ResponseEntity<>(this.commentService.getCommentById(commentID), HttpStatus.OK);
    }
    
    @PostMapping(path = "/comments", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void createComment(@RequestParam Map<String, String> params) {
        Comment comment = new Comment();
        comment.setRating(Integer.parseInt(params.get("rating")));
        comment.setCommentText(params.get("commentText"));
        comment.setCreatedAt(new Date());
        
        // Chuyển đổi userID từ chuỗi sang đối tượng User
        int userId = Integer.parseInt(params.get("userID"));
        User user = userService.getUserById(userId);
        comment.setUserID(user);

       // Chuyển đổi companyID từ chuỗi sang đối tượng Company
        int companyId = Integer.parseInt(params.get("companyID"));
        Company company = companyService.getCompaniesById(companyId);
        comment.setCompanyID(company);
        this.commentService.addOrUpdate(comment);
    }
    
    @DeleteMapping("/comments/{commentID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "companyID") int companyID) {
        this.companyService.deleteCompany(companyID);
    }
}

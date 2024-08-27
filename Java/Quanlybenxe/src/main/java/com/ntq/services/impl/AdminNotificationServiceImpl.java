package com.ntq.services.impl;

import com.ntq.pojo.Company;
import com.ntq.services.AdminNotificationService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AdminNotificationServiceImpl implements AdminNotificationService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void notifyAdmin(Company company) {
        String adminServiceUrl = "http://admin-service/verify"; // URL dịch vụ của admin
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("companyName", company.getCompanyName());
        requestBody.put("address", company.getAddress());
        requestBody.put("email", company.getEmail());
        requestBody.put("phoneNumber", company.getPhoneNumber());
        requestBody.put("avatar", company.getAvatar());
        requestBody.put("isShippingAvailable", company.getIsShippingAvailable());
        requestBody.put("createdAt", company.getCreatedAt());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            restTemplate.postForEntity(adminServiceUrl, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
       
        }
    }
}


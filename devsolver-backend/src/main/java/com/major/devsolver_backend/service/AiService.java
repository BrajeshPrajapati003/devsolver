package com.major.devsolver_backend.service;

import com.major.devsolver_backend.dto.AiTagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AiService {

    private final ChatClient.Builder chatClientBuilder;

    /**
     * AI TAG Suggestions
     */
    public AiTagResponse suggestTags(String content){

        String prompt = """
                Analyze the following developer post content.
                
                Suggest 3 to 5 short technical tags.
                
                Rules:
                - lowercase only
                - no explanations
                - comma separated
                - no hashtags
                
                Content:
                """ + content;

        String response = chatClientBuilder.build().prompt()
                .user(prompt)
                .call().content();

        Set<String> tags = Arrays.stream(response.split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        return AiTagResponse.builder()
                .tags(tags)
                .build();
    }

}

package com.example.appwebhooktelegrambot.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.nio.file.Files;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendDocumentOwn {

    @JsonProperty(value = "chat_id")
    private String chat_id;

    private String caption;

    private String document;

    public MultiValueMap<String, Object> getRequestBody() throws Exception {
        MultiValueMap<String, Object> res = new LinkedMultiValueMap<>();
        res.add("chat_id", this.chat_id);
        res.add("caption", this.caption);
        File file = new File(this.document);
        ByteArrayResource fileAsResource = new ByteArrayResource(Files.readAllBytes(file.toPath())) {
            @Override
            public String getFilename() {
                String[] x = document.split("\\\\");
                return x[x.length - 1];
            }
        };
        res.add("document", fileAsResource);
        return res;
    }
}

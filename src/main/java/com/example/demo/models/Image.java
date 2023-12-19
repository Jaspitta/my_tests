package com.example.demo.models;

import com.example.demo.utils.ValidatorUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import software.amazon.awssdk.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {

    /**
     * URL from where the image data can be fetched through a web browser.
     */
    @NotNull
    private String url;

    /**
     * URL from where a thumbnail of the image can be fetched through a webbrowser.
     */
    private String thumbnail;

    /**
     * Image type like: gif, jpeg, png, svg
     */
    @NotNull
    private String type;

    /**
     * Image category :CHARGER,ENTRANCE,LOCATION,NETWORK,OPERATOR,OTHER,OWNER
     */
    private Category category;

    /**
     * Width of the full scale image
     */
    private Integer width;

    /**
     * Height of the full scale image
     */
    private Integer height;

    public static List<String> validateUrl(Image img) {
        List<String> errors = new ArrayList();
        errors.add(img.getUrl() != null && !ValidatorUtils.isValidUrl(img.getUrl()) ? "Image url is not valid" : null);
        List<String> errorResp = new ArrayList();

        errors.forEach((err) -> {
            if (err != null) errorResp.add(err);
        });
        return errorResp;
    }

}

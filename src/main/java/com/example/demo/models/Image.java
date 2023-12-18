package com.example.demo.models;

import com.example.demo.utils.ValidatorUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import software.amazon.awssdk.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


/**
 * @author calogero.corbo
 * <p>
 * This class references images related to a EVSE in terms of a file name or url.
 * According to the roaming connection between one EVSE Operator and one or more
 * Navigation Service Providers the hosting or file exchange of image payload data
 * has to be defined. The exchange of this content data is out of scope of OCHP.
 * However, the recommended setup is a public available web server hosted and updated
 * by the EVSE Operator. Per charge point an unlimited number of images of each type
 * is allowed. Recommended are at least two images where one is a network or provider
 * logo and the second is a station photo. If two images of the same type are defined
 * they should be displayed additionally, not optionally.
 * <p>
 * Photo Dimensions: The recommended dimensions for all photos is a minimum of 800 pixels
 * wide and 600 pixels height. Thumbnail representations for photos should always have
 * the same orientation as the original with a size of 200 to 200 pixels.
 * <p>
 * Logo Dimensions: The recommended dimensions for logos are exactly 512 pixels wide and
 * 512 pixels height. Thumbnail representations for logos should be exactly 128 pixels
 * in width and height. If not squared, thumbnails should have the same orientation as the original.
 */
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

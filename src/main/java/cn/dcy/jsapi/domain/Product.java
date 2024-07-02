package cn.dcy.jsapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Kyle
 * @date 2024/06/07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product{
    private String id;
    private String image;
    private String name;
    private Rating rating;
    private int priceCents;
    private List<String> keywords;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Rating {
        private double stars;
        private int count;
    }
}

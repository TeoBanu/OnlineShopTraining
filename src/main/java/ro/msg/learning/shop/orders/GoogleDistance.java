package ro.msg.learning.shop.orders;

import lombok.Data;

import java.util.List;

@Data
public class GoogleDistance {

    private List<GoogleDistanceRow> rows;

    @Data
    public static class GoogleDistanceRow {
        private List<GoogleDistanceRowElement> elements;
    }

    @Data
    public static class GoogleDistanceRowElement {
        private GoogleDistanceRowElementDetails distance;
        private GoogleDistanceRowElementDetails duration;
        private String status;
    }

    @Data
    public static class GoogleDistanceRowElementDetails {
        private long value;
        private String text;
    }
}

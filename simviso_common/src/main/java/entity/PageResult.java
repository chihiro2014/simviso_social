package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 1/24/2019
 * @Email: liujch1996@gmail.com
 */

@Getter
@Setter
public class PageResult<T> {

    private long total;
    private List<T> rows;


    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResult() {
    }
}

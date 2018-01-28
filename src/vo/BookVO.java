package vo;

import java.util.Date;

/**
 * Created by Administrator on 2017-11-29.
 */
public class BookVO {
    private String title;
    private String isbn;
    private String writer;
    private Integer page;
    private Integer price;
    private Date pubilsh_date;
    private String publisher;

    public BookVO(String title, String isbn, String writer, Integer page, Integer price, Date pubilsh_date, String publisher) {
        this.title = title;
        this.isbn = isbn;
        this.writer = writer;
        this.page = page;
        this.price = price;
        this.pubilsh_date = pubilsh_date;
        this.publisher = publisher;
    }

    public BookVO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getPubilsh_date() {
        return pubilsh_date;
    }

    public void setPubilsh_date(Date pubilsh_date) {
        this.pubilsh_date = pubilsh_date;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "BookVO{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", writer='" + writer + '\'' +
                ", page=" + page +
                ", price=" + price +
                ", pubilsh_date=" + pubilsh_date +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}

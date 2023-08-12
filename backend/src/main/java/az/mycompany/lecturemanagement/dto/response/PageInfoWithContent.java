package az.mycompany.lecturemanagement.dto.response;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageInfoWithContent<T> {
    private int totalPages;
    private int currentPage;
    private List<T> content;

    public PageInfoWithContent(Page<T> page) {
        this.totalPages = page.getTotalPages();
        this.currentPage = page.getNumber();
        this.content = page.getContent();
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<T> getContent() {
        return content;
    }

}


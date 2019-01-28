package com.trainingapp.trainingapp_web.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ViewModelPost {

    private Long id;

    @NotBlank(message = "Title cannot be empty.")
    @Length(min = 5, max = 100, message="Title must be between 5-100 characters.")
    private String title;

    @NotBlank(message = "Subtitle cannot be empty.")
    @Length(min = 5, max = 200, message="Subtitle must be between 5-200 characters.")
    private String subtitle;

    private String leadImage;

    @NotBlank(message = "Post body cannot be empty.")
    @Length(min = 5, max = 50000, message="Description must be between 5-50000 characters.")
    private String body;
    private LocalDateTime date;

    private ViewModelUser user;
    private List<ViewModelPostVote> postVotes;

    @Override
    public String toString() {
        return "ViewModelPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", leadImage='" + leadImage + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                ", user=" + user +
                ", postVotes=" + postVotes +
                '}';
    }

    public ViewModelPost(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getLeadImage() {
        return leadImage;
    }

    public void setLeadImage(String leadImage) {
        this.leadImage = leadImage;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ViewModelUser getUser() {
        return user;
    }

    public void setUser(ViewModelUser user) {
        this.user = user;
    }

    public List<ViewModelPostVote> getPostVotes() {
        return postVotes;
    }

    public void setPostVotes(List<ViewModelPostVote> postVotes) {
        this.postVotes = postVotes;
    }

    @JsonGetter("hoursMinutes")
    public String hoursMinutes() {
        return date.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    @JsonGetter("formatDate")
    public String formatDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
    }

    // VOTING LOGIC =============================================================================
    @JsonGetter("voteCount") // saying that this method is only being used as an attribute in show.html
    public int voteCount() {
        if(postVotes != null) {
            return postVotes.stream().mapToInt(ViewModelPostVote::getType).reduce(0, (sum, vote) -> sum + vote);
        } else {
            return 0;
        }
        // takes all the votes and adds 1 or -1 (getType). Needs more users in application to vote and see results.
        // http://www.baeldung.com/java-8-double-colon-operator (::)
        // stream(), mapToInt(), reduce()
        // https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
        // A stream represents a sequence of elements and supports different kinds of operations to perform computations upon those elements.
        // Streams can be created from various data sources, especially collections. Lists and Sets support new methods stream()
        // mapToInt() returns an IntStream consisting of the results of applying the given function to the elements of this stream.
        // PostVote::getType will evaluate to a function that invokes getType() directly without any delegation.
        // There’s a really tiny performance difference due to saving one level of delegation.
        // reduce() sums the values
    }

    public ViewModelPostVote getVoteFrom(ViewModelUser user) {
        for (ViewModelPostVote vote : postVotes) {
            if (vote.voteBelongsTo(user)) {
                return vote;
            }
        }
        return null;
    }

    public void addVote(ViewModelPostVote vote) {
        postVotes.add(vote);
    }

    public void removeVote(ViewModelPostVote vote) {
        postVotes.remove(vote);
    }



    // MARKDOWN PARSING FOR VIEW ==============================================================

    public String getHtmlTitle() {
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(parser.parse(title));
    }

    public String getHtmlLeadImage() {
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(parser.parse(leadImage));
    }

    public String getHtmlSubtitle() {
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(parser.parse(subtitle));
    }

    public String getHtmlBody() {
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(parser.parse(body));
    }
}

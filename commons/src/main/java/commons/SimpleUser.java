package commons;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * To be mostly used for communication between client and server, since client doesn't need nearly as much info as put into Player class
 */
@Entity
public class SimpleUser {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "")
    private int gameInstanceId;

    @Column(name = "name")
    private String name;

    @Column(name = "score")
    private int score;

    /* Don't think we need a cookie for single player
    @Column(name = "cookie")
    private String cookie;
*/

    public SimpleUser() {

    }

    public SimpleUser(long id, String name, int gameInstanceId) {
        this.name = name;
        this.id = id;
        this.gameInstanceId = gameInstanceId;
        //this.cookie = cookie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void setId(long id) {
        this.id = id;
    }
/*
    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
*/
    public int getGameInstanceId() {
        return gameInstanceId;
    }

    public void setGameInstanceId(int gameInstanceId) {
        this.gameInstanceId = gameInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SimpleUser that = (SimpleUser) o;

        return new EqualsBuilder().append(id, that.id).append(score, that.score).append(name, that.name)//.append(cookie, that.cookie)
        .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(name).append(score)//.append(cookie)
        .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("gameInstanceId", gameInstanceId)
                .append("name", name)
                .append("score", score)
                //.append("cookie", cookie)
                .toString();
    }

    public Player toPlayer(GameInstance gameInstance) {
        return new Player(id, name, gameInstance,null);
    }
/*
    public SimpleUser unsafe() {
        return new SimpleUser(id, name, gameInstanceId, null);
    }
*/
}

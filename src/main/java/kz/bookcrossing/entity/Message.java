package kz.bookcrossing.entity;

import kz.bookcrossing.config.EnumTypePostgreSql;
import kz.bookcrossing.enums.MessageState;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_message")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@TypeDefs({
        @TypeDef(name = "enum_postgre", typeClass = EnumTypePostgreSql.class)
})
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "conversation_id")
    private Long conversationId;

    @Column(name = "ad_data_id")
    private Long adDataId;

    @Column(name = "date")
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Type(type = "enum_postgre")
    @Column(name = "state")
    private MessageState state;

    @Column(name = "text")
    private String text;

    @Column(name = "from_user")
    private String fromUser;

    @Column(name = "to_user")
    private String toUser;
}

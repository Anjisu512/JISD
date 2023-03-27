package kr.co.an.jsd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
 
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Customer extends CustomerTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cno; //고객센터 글번호
	
	@Column(length = 200, nullable=false)
	private String title; // 글제목
	
	@Column(length = 200, nullable=false)
	private String content; // 글내용
	
	@Column(length = 200, nullable=false)
	private String writer; // 작성자
	
	@Column(length = 200, nullable=false)
	private int cstate; //삭제여부
	
	public void change(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
}

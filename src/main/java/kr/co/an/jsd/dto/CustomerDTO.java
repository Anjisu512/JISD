package kr.co.an.jsd.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
	
	private long cno;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String content;
	
	@NotEmpty
	private String writer;
	
	private int cstate;
	private LocalDateTime regDate;		//게시일
	private LocalDateTime modDate;		//수정일
	
}

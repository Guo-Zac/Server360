package com.zacguo.server360;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notelists")
public class NoteLists {
	
	private Integer noteId;
	private String noteName;
	
	@Id
	@Column(name = "noteId")
	public Integer getNoteId() {
		return noteId;
	}
	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}
	
	@Column(name = "noteName")
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	
}

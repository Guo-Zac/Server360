package com.zacguo.server360;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notetexts")
public class NoteTexts {
	
	private Integer textId;
	private Integer noteId;
	private String textContent;
	
	@Id
	@Column(name = "textId")
	public Integer getTextId() {
		return textId;
	}
	public void setTextId(Integer textId) {
		this.textId = textId;
	}
	
	@Column(name = "noteId")
	public Integer getNoteId() {
		return noteId;
	}
	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}
	
	@Column(name = "textContent")
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	
	
}

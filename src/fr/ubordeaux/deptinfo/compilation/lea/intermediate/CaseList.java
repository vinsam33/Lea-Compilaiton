package fr.ubordeaux.deptinfo.compilation.lea.intermediate;

public class CaseList {
	private CASE value_head;
	private CaseList next;
	private CaseList prev;

	public CaseList(CASE value_head, CaseList next, CaseList prev) {
		super();
		this.value_head = value_head;
		this.next = next;
		this.prev = prev;

	}

	public CaseList(CASE value_head) {
		this(value_head, null, null);
	}

	public CASE getHead() {
		return value_head;
	}

	public CaseList getNext() {
		return next;
	}

	public CaseList getPrev() {
		return prev;
	}
}

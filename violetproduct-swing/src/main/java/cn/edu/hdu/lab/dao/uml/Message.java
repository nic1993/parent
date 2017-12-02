package cn.edu.hdu.lab.dao.uml;

import java.util.ArrayList;
import java.util.List;

import com.horstmann.violet.application.gui.DisplayForm;

public class Message implements Cloneable{
	@Override
	public Object clone() {   
		Message o = null;   
        try {   
            o = (Message) super.clone();   
        } catch (CloneNotSupportedException e) {   
            e.printStackTrace();   
        }  
        o.setConditions(new ArrayList<String>(this.conditions));
        
        o.setStimulate((Stimulate)stimulate.clone());
        return o;   
    }
	
	private String id;
	/*
	 * @noteID:平台上ID设置过于混乱，此ID用于映射到消息上的唯一标识id；
	 * 不在平台上修改引用id的程序（修改过于麻烦）;
	 */
	private String noteID;
	private String name;
	private String senderID;
	private String receiverID;
	
	private String sender;       
	private String receiver;   
	
	private Stimulate stimulate;//消息中的激励消息
	
	private String returnValue;
	private String returnValueType;
	private double prob;
	
	private boolean isLast=false;
	
	private boolean isInFragment=false; // 消息是否在组合片段的标记
	private String fragmentId;
	private String fragType;
	private String operandId;//消息在组合片段中对应的操作域ID
	
	private String fragFlag;  //消息进出组合片段标记   inOperand+outOperand

	private String notation=""; //消息执行条件
	
	private String exectime;//消息执行时间约束
	private String fromTimeConstraint;//消息起始状态时间约束
	//新添加的属性
	private List<String> conditions=new ArrayList<String>();
	private boolean isTranslationed=false;
	
	private double pointY=0;
	
	public Message(){}
	public void set(String id, String name, String senderID, String receiverID) {
		this.id=id;
		this.name=name;
		this.senderID=senderID;
		this.receiverID=receiverID;
	}
	public void  addCondition(String str)
	{
		conditions.add(str);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", senderID=");
		builder.append(senderID);
		builder.append(", receiverID=");
		builder.append(receiverID);
		builder.append(", sender=");
		builder.append(sender);
		builder.append(", receiver=");
		builder.append(receiver);
		builder.append(", stimulate=");
		builder.append(stimulate);
		builder.append(", prob=");
		builder.append(prob);
		builder.append(", exectime=");
		builder.append(exectime);
		builder.append(", fromTimeConstraint=");
		builder.append(fromTimeConstraint);
		builder.append(", isLast=");
		builder.append(isLast);
		builder.append(", isInFragment=");
		builder.append(isInFragment);
		builder.append(", fragmentId=");
		builder.append(fragmentId);
		builder.append(", fragType=");
		builder.append(fragType);
		builder.append(", operandId=");
		builder.append(operandId);
		builder.append(", fragFlag=");
		builder.append(fragFlag);
		builder.append(", notation=");
		builder.append(notation);
		builder.append(", conditions=");
		builder.append(conditions);
		builder.append(", isTranslationed=");
		builder.append(isTranslationed);
		builder.append("]");
		return builder.toString();
	}
	public void print_Message() {
		System.out.println("Message: id=" + id + "\tname=" + name +"\treturnValue="+returnValue+"\treturnValueType="+returnValueType
				+"\tstimulate="+stimulate+ "\tsenderID=" + senderID
				+ "\treceiverID=" + receiverID + "\tsender=" + sender + "\treceiver="+ receiver 
				+"\tprob="+prob+"\texectime="+exectime+"\tfromTimeConstraint="+fromTimeConstraint+"\tisLast="+isLast
				
				+ "\t是否在组合片段中=" + isInFragment + "\t 所属组合片段Id=" + fragmentId+"\t\t 组合片段类型=" + fragType+"\t所属操作ID="+ operandId
				+ "\t消息进出组合片段标记=" + fragFlag  + "\tnotation=" + notation +"\t Y="+pointY );
		
		DisplayForm.mainFrame.getOutputinformation().geTextArea().append("Message: id=" + id + "\tname=" + name +"\treturnValue="+returnValue+"\treturnValueType="+returnValueType
				+"\tstimulate="+stimulate+ "\tsenderID=" + senderID
				+ "\treceiverID=" + receiverID + "\tsender=" + sender + "\treceiver="+ receiver 
				+"\tprob="+prob+"\texectime="+exectime+"\tfromTimeConstraint="+fromTimeConstraint+"\tisLast="+isLast
				
				+ "\t是否在组合片段中=" + isInFragment + "\t 所属组合片段Id=" + fragmentId+"\t\t 组合片段类型=" + fragType+"\t所属操作ID="+ operandId
				+ "\t消息进出组合片段标记=" + fragFlag  + "\tnotation=" + notation +"\t Y="+pointY  + "\n");
		int length11 = DisplayForm.mainFrame.getOutputinformation().geTextArea().getText().length(); 
		DisplayForm.mainFrame.getOutputinformation().geTextArea().setCaretPosition(length11);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNoteID() {
		return noteID;
	}
	public void setNoteID(String noteID) {
		this.noteID = noteID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSenderID() {
		return senderID;
	}
	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}
	public String getReceiverID() {
		return receiverID;
	}
	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID;
	}
	public String getFragFlag() {
		return fragFlag;
	}
	public void setFragFlag(String fragFlag) {
		this.fragFlag = fragFlag;
	}
	public double getProb() {
		return prob;
	}
	public void setProb(double prob) {
		this.prob = prob;
	}
	public String getExectime() {
		return exectime;
	}
	public void setExectime(String exectime) {
		this.exectime = exectime;
	}
	public boolean isInFragment() {
		return isInFragment;
	}
	public void setInFragment(boolean isInFragment) {
		this.isInFragment = isInFragment;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getOperandId() {
		return operandId;
	}
	public void setOperandId(String operandId) {
		this.operandId = operandId;
	}
	public String getNotation() {
		return notation;
	}
	public void setNotation(String notation) {
		this.notation = notation;
	}
	public String getFragmentId() {
		return fragmentId;
	}
	public void setFragmentId(String fragmentId) {
		this.fragmentId = fragmentId;
	}
	public String getFragType() {
		return fragType;
	}
	public void setFragType(String fragType) {
		this.fragType = fragType;
	}
	public boolean isLast() {
		return isLast;
	}
	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}
	public List<String> getConditions() {
		return conditions;
	}
	public void setConditions(List<String> conditions) {
		this.conditions = conditions;
	}
	public boolean isTranslationed() {
		return isTranslationed;
	}
	public void setTranslationed(boolean isTranslationed) {
		this.isTranslationed = isTranslationed;
	}
	public Stimulate getStimulate() {
		return stimulate;
	}
	public void setStimulate(Stimulate stimulate) {
		this.stimulate = stimulate;
	}
	public String getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}
	public String getReturnValueType() {
		return returnValueType;
	}
	public void setReturnValueType(String returnValueType) {
		this.returnValueType = returnValueType;
	}
	public double getPointY() {
		return pointY;
	}
	public void setPointY(double pointY) {
		this.pointY = pointY;
	}
	public String getFromTimeConstraint() {
		return fromTimeConstraint;
	}
	public void setFromTimeConstraint(String fromTimeConstraint) {
		this.fromTimeConstraint = fromTimeConstraint;
	}	
	
	
}

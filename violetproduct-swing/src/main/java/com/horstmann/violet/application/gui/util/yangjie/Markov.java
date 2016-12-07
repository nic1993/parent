package com.horstmann.violet.application.gui.util.yangjie;

import java.util.List;

/**
 * ������ʾmarkov���������ṹ������һϵ��get��set������
 * 
 * @author YJ
 * @version 1.0
 * */

public class Markov {

	private List<State> states; // ��ͷ��㼯��
	private int numberOfStates; // ����markov����״̬�ڵ����
	private List<Transition> transitions; // ����markov����Ǩ�Ƽ���
	private State initialState; // ��ʼ״̬�ڵ�
	private State finalState; // ��ֹ״̬�ڵ�
	// �������Markov���ϵ�����·�����ϣ�ÿ��·����һ��Ǩ�����м���
	private List<Route> routeList;
	private int tcNumber;

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

	public State getInitialState() {
		return initialState;
	}

	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}

	public State getFinalState() {
		return finalState;
	}

	public void setFinalState(State finalState) {
		this.finalState = finalState;
	}

	public int getNumberOfStates() {
		return numberOfStates;
	}

	public void setNumberOfStates(int numberOfStates) {
		this.numberOfStates = numberOfStates;
	}

	public List<Route> getRouteList() {
		return routeList;
	}

	public void setRouteList(List<Route> routeList) {
		this.routeList = routeList;
	}

	public int getTcNumber() {
		return tcNumber;
	}

	public void setTcNumber(int tcNumber) {
		this.tcNumber = tcNumber;
	}

}
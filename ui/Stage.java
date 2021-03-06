package ui;

import java.awt.Container;
import java.awt.Graphics2D;
import java.util.ArrayList;

import ui.elem.Element;

public abstract class Stage {
    private ArrayList<Element> elementList = new ArrayList<Element>();
    
    private long ticks = 0;
    
    private int stageIncrement = 0;
    
    private int id = -1;
    
    private Container containerVar;
    
    public void setID(int intVar) {
    	id = intVar;
    }
    
    public int getID() {
    	return id;
    }
    
    /**
     * Called when this stage becomes the current stage
     */
    public abstract void init();
    
    /**
     * Called whenever a choice is clicked by the mouse
     * @param elementVar The element that invoked this method
     */
    public abstract void choiceClicked(Element elementVar);
    
    /**
     * Called after a task is scheduled after certain amount of ticks
     */
    public abstract void taskPerformed();
    
    /**
     * Simple task scheduling method, after specified number of ticks, run taskPerformed
     * @param tickNumber The number of ticks, a tick is 1/20 of a second
     */
    public void scheduleTask(long tickNumber) { 
        ticks += tickNumber; 
        if(tickNumber == 0) {
        	ticks = 0;
        	this.taskPerformed();
        }
    }
    
    public void decreaseTicks() {
        if(ticks > 0) {
            ticks--;
            if(ticks == 0) this.taskPerformed();
        }
    }
    
    /**
     * Add a new element to this stage;
     * @param elementVar The element(s)
     */
    public void add(Element... elementVar) {
        for(Element element : elementVar) {
            if(elementList.indexOf(elementVar) < 0) {
                elementList.add(element);
            }
        }
    }
    
    /**
     * Remove the given elements from this stage
     * @param elementVar The element(s)
     */
    public void remove(Element... elementVar) {
        for(Element element : elementVar) { 
            elementList.remove(element);
        }
    }
    
    public void drawAll(Graphics2D render) {
    	for(int i = 0; i < elementList.size(); i++) {
            Element element = elementList.get(i);
            if(element != null) element.drawElement(render, containerVar);
        }
    }
    
    public void update() {
        for(int i = 0; i < elementList.size(); i++) {
            Element element = elementList.get(i);
            element.updateElement(containerVar);
        }
    }

    public void removeAll() {
        elementList.clear();
    }
    
    /**
     * Get the element list of this stage
     */
    public ArrayList<Element> getElements() {
    	return elementList;
    }
    
    /**
     * Go to the next stage in the list
     */
    public void nextStage() {
    	if(stageIncrement > 0) return;
    	stageIncrement = 1;
    }
    
    /**
     * Go to the previous stage in the list
     */
    public void prevStage() {
    	if(stageIncrement > 0) return;
        stageIncrement = 2;
    }
    
    /**
     * Set the current stage to the specified stage
     * @param stageID The ID of the stage in the list
     */
    public void setStage(int stageID) {
    	if(stageIncrement > 0) return;
    	if(stageID < 0) stageID = 0;
    	stageIncrement = 3 + stageID;
    }
    
    public int incrementVar() {
    	return stageIncrement;
    }
    
    public void resetIncrement() {
    	stageIncrement = 0;
    }
    
    public int getWidth() {
    	return containerVar.getWidth();
    }
    
    public int getHeight() {
    	return containerVar.getHeight();
    }
    
    public void setContainer(Container c) {
    	containerVar = c;
    }
}

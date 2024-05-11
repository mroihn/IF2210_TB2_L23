public abstract class Harvestable extends Card {
    private Integer weight_or_age;
    private Integer HarvestLimit;
    
    abstract Product Harvest();

    public boolean isReady();

    public void delay();

    public void instantHarvest();
    
}

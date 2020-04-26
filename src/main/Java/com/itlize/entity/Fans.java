package com.itlize.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name="fans")
public class Fans {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="manufacturer")
    private String manufacturer;

    @Column(name="series")
    private String series;

    @Column(name="model")
    private String model;

    @Column(name="useType")
    private String useType;

    @Column(name="application")
    private String application;

    @Column(name="mountingLocation")
    private String mountingLocation;

    @Column(name="accessories")
    private String accessories;

    @Column(name="modelYear")
    private int modelYear;

    @Column(name="airflow")
    private int airflow;

    @Column(name="powerMin")
    private double powerMin;

    @Column(name="powerMax")
    private double powerMax;

    @Column(name="operatingVoltageMin")
    private int operatingVoltageMin;

    @Column(name="operatingVoltageMax")
    private int operatingVoltageMax;

    @Column(name="fanSpeedMin")
    private int fanSpeedMin;

    @Column(name="fanSpeedMax")
    private int fanSpeedMax;

    @Column(name="numFanSpeeds")
    private int numFanSpeeds;

    @Column(name="sound")
    private int sound;

    @Column(name="sweepDiameter")
    private int sweepDiameter;

    @Column(name="heightMin")
    private double heightMin;

    @Column(name="heightMax")
    private double heightMax;

    @Column(name="weight")
    private double weight;

    @Column(name="prodDetails",length=100000)
    private String prodDetails;

    @Column(name="specSheet")
    private String specSheet;

    @Column(name="price")
    private Double price;

    @Column(name="image")
    private String image;

    @JsonIgnore
    @OneToMany(targetEntity = ProjectToFans.class,cascade = CascadeType.REMOVE,mappedBy = "fans")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<ProjectToFans> projects = new HashSet<ProjectToFans>();

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getMountingLocation() {
        return mountingLocation;
    }

    public void setMountingLocation(String mountingLocation) {
        this.mountingLocation = mountingLocation;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public int getAirflow() {
        return airflow;
    }

    public void setAirflow(int airflow) {
        this.airflow = airflow;
    }

    public double getPowerMin() {
        return powerMin;
    }

    public void setPowerMin(double powerMin) {
        this.powerMin = powerMin;
    }

    public double getPowerMax() {
        return powerMax;
    }

    public void setPowerMax(double powerMax) {
        this.powerMax = powerMax;
    }

    public int getOperatingVoltageMin() {
        return operatingVoltageMin;
    }

    public void setOperatingVoltageMin(int operatingVoltageMin) {
        this.operatingVoltageMin = operatingVoltageMin;
    }

    public int getOperatingVoltageMax() {
        return operatingVoltageMax;
    }

    public void setOperatingVoltageMax(int operatingVoltageMax) {
        this.operatingVoltageMax = operatingVoltageMax;
    }

    public int getFanSpeedMin() {
        return fanSpeedMin;
    }

    public void setFanSpeedMin(int fanSpeedMin) {
        this.fanSpeedMin = fanSpeedMin;
    }

    public int getFanSpeedMax() {
        return fanSpeedMax;
    }

    public void setFanSpeedMax(int fanSpeedMax) {
        this.fanSpeedMax = fanSpeedMax;
    }

    public int getNumFanSpeeds() {
        return numFanSpeeds;
    }

    public void setNumFanSpeeds(int numFanSpeeds) {
        this.numFanSpeeds = numFanSpeeds;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public int getSweepDiameter() {
        return sweepDiameter;
    }

    public void setSweepDiameter(int sweepDiameter) {
        this.sweepDiameter = sweepDiameter;
    }

    public double getHeightMin() {
        return heightMin;
    }

    public void setHeightMin(double heightMin) {
        this.heightMin = heightMin;
    }

    public double getHeightMax() {
        return heightMax;
    }

    public void setHeightMax(double heightMax) {
        this.heightMax = heightMax;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getProdDetails() {
        return prodDetails;
    }

    public void setProdDetails(String prodDetails) {
        this.prodDetails = prodDetails;
    }

    public String getSpecSheet() {
        return specSheet;
    }

    public void setSpecSheet(String specSheet) {
        this.specSheet = specSheet;
    }

    public Collection<ProjectToFans> getProjects() {
        return projects;
    }

    public void addProjects(ProjectToFans project) {
        if(projects.contains(project))
            return;
        projects.add(project);
        project.setFans(this);
    }
    public void removeProjects(ProjectToFans project){
        if(!projects.contains(project)){
            return;
        }
        projects.remove(project);
        project.setFans(null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice(){return price;}

    public void setPrice(double price){this.price=price;}

    public String getImageLink(){return image;}

    public void setImageLink(String link){this.image=link;}

    @Override
    public String toString(){
    return "Fan [id: "+id+"\nManufacturer: "+manufacturer+"\nSeries: "+series+"\nModel: "+model+"\nUse Type: "+useType+"\nApplication: "+application+
            "\nMounting Location: "+mountingLocation+"\nAccessories: "+accessories+"\nModel Year: "+modelYear+"\nAirflow (CFM): "+airflow+
            "\nPower (W): Min="+powerMin+" Max="+powerMax+"\nOperatingVoltage (VAC): Min="+operatingVoltageMin+" Max="+operatingVoltageMax+"\nFan Speed (RPM) Min="+fanSpeedMin+" Max="+fanSpeedMax+
                "\nNumber of fan speeds: "+numFanSpeeds+"\nSound at max speed (dBA): "+sound+"\nFan sweep diameter (in): "+sweepDiameter+"\nHeight (in): Min="+heightMin+" Max="+heightMax+
                "\nWeight (lbs): "+weight+"]";

    }

    public String toJson(List<String> entries){
        String ret = null;
        List<String> colsContent = new ArrayList<String>();
        for( String entry : entries){
            colsContent.add(entry);
        }
        ret = "{" + String.join(",",colsContent) + "}";
        return String.format("{\"fanId\": \"%d\", \"content\": \"%s\"}", getId(),ret);
    }
}

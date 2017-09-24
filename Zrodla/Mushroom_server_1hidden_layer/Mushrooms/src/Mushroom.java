/**
 * Created by kacper on 2017-04-06.
 */

//
//        Attribute Information: (classes: edible=e, poisonous=p)
//
//        cap-shape: bell=b,conical=c,convex=x,flat=f, knobbed=k,sunken=s
//        cap-surface: fibrous=f,grooves=g,scaly=y,smooth=s
//        cap-color: brown=n,buff=b,cinnamon=c,gray=g,green=r,pink=p,purple=u,red=e,white=w,yellow=y
//        bruises: bruises=t,no=f
//        odor: almond=a,anise=l,creosote=c,fishy=y,foul=f,musty=m,none=n,pungent=p,spicy=s
//        gill-attachment: attached=a,descending=d,free=f,notched=n
//        gill-spacing: close=c,crowded=w,distant=d
//        gill-size: broad=b,narrow=n
//        gill-color: black=k,brown=n,buff=b,chocolate=h,gray=g, green=r,orange=o,pink=p,purple=u,red=e,white=w,yellow=y
//        stalk-shape: enlarging=e,tapering=t
//        stalk-root: bulbous=b,club=c,cup=u,equal=e,rhizomorphs=z,rooted=r,missing=? -> "m"
//        stalk-surface-above-ring: fibrous=f,scaly=y,silky=k,smooth=s
//        stalk-surface-below-ring: fibrous=f,scaly=y,silky=k,smooth=s
//        stalk-color-above-ring: brown=n,buff=b,cinnamon=c,gray=g,orange=o,pink=p,red=e,white=w,yellow=y
//        stalk-color-below-ring: brown=n,buff=b,cinnamon=c,gray=g,orange=o,pink=p,red=e,white=w,yellow=y
//        veil-type: partial=p,universal=u
//        veil-color: brown=n,orange=o,white=w,yellow=y
//        ring-number: none=n,one=o,two=t
//        ring-type: cobwebby=c,evanescent=e,flaring=f,large=l,none=n,pendant=p,sheathing=s,zone=z
//        spore-print-color: black=k,brown=n,buff=b,chocolate=h,green=r,orange=o,purple=u,white=w,yellow=y
//        population: abundant=a,clustered=c,numerous=n,scattered=s,several=v,solitary=y
//        habitat: grasses=g,leaves=l,meadows=m,paths=p,urban=u,waste=w,woods=d

enum Clasification{e, p};
enum CapShape{b, c, x, f, k, s};
enum CapSurface{f,g,y,s};
enum CapColor{n, b, c, g, r, p, u, e, w, y};
enum Bruises{t, f};
enum Odor{a, l, c, y, f, m, n, p, s};
enum GillAttachment{a, d, f, n};
enum GillSpacing{c, w, d};
enum GillSize{b, n};
enum GillColor{k, n, b, h, g, r, o, p, u, e, w, y};
enum StalkShape{e, t};
enum StalkRoot{b, c, u, e, z, r, m};
enum StalkSurfaceAboveRing{f,y,k,s};
enum StalkSurfaceBelowRing{f,y,k,s};
enum StalkColorAboveRing{n,b,c,g,o,p,e,w,y};
enum StalkColorBelowRing{n,b,c,g,o,p,e,w,y};
enum VeilType{p,u};
enum VeilColor{n,o,w,y};
enum RingNumber{n,o,t};
enum RingType{c,e,f,l,n,p,s,z};
enum SporePrintColor{k,n,b,h,r,o,u,w,y};
enum Population{a,c,n,s,v,y};
enum Habitat{g,l,m,p,u,w,d};


public class Mushroom {

    private Clasification clasification;
    private CapSurface capSurface;
    private CapShape capShape;
    private CapColor capColor;
    private Bruises bruises;
    private Odor odor;
    private GillAttachment gillAttachment;
    private GillSpacing gillSpacing;
    private GillSize gillSize;
    private GillColor gillColor;
    private StalkShape stalkShape;
    private StalkRoot stalkRoot;
    private StalkSurfaceAboveRing stalkSurfaceAboveRing;
    private StalkSurfaceBelowRing stalkSurfaceBelowRing;
    private StalkColorAboveRing stalkColorAboveRing;
    private StalkColorBelowRing stalkColorBelowRing;
    private VeilType veilType;
    private VeilColor veilColor;
    private RingNumber ringNumber;
    private RingType ringType;
    private SporePrintColor sporePrintColor;
    private Population population;
    private Habitat habitat;

    public Clasification getClasification() {
        return clasification;
    }

    public Integer getClasificationOrdinalValue(){
        return clasification.ordinal();
    }

    public void setClasification(Clasification clasification) {
        this.clasification = clasification;
    }

    public CapShape getCapShape() {
        return capShape;
    }

    public Integer getCapShapeOrdinalValue(){
        return capShape.ordinal();
    }

    public void setCapShape(CapShape capShape) {
        this.capShape = capShape;
    }

    public CapSurface getCapSurface() {
        return capSurface;
    }

    public Integer getCapSurfaceOrdinalValue(){
       return capSurface.ordinal();
     }

    public void setCapSurface(CapSurface capSurface) {
        this.capSurface = capSurface;
    }

    public CapColor getCapColor() {
        return capColor;
    }

    public Integer getCapColorOrdinalValue(){
        return capColor.ordinal();
    }

    public void setCapColor(CapColor capColor) {
        this.capColor = capColor;
    }

    public Bruises getBruises() {
        return bruises;
    }

    public Integer getBruisesOrdinalValue(){
        return bruises.ordinal();
    }

    public void setBruises(Bruises bruises) {
        this.bruises = bruises;
    }

    public Odor getOdor() {
        return odor;
    }

    public Integer getOdorOrdinalValue(){
        return odor.ordinal();
    }

    public void setOdor(Odor odor) {
        this.odor = odor;
    }

    public GillAttachment getGillAttachment() {
        return gillAttachment;
    }

    public Integer getGillAttachmentOrdinalValue(){
        return gillAttachment.ordinal();
    }

    public void setGillAttachment(GillAttachment gillAttachment) {
        this.gillAttachment = gillAttachment;
    }

    public GillSpacing getGillSpacing() {
        return gillSpacing;
    }

    public Integer getGillSpacingOrdinalValue(){
        return gillSpacing.ordinal();
    }

    public void setGillSpacing(GillSpacing gillSpacing) {
        this.gillSpacing = gillSpacing;
    }

    public GillSize getGillSize() {
        return gillSize;
    }

    public Integer getGillSizeOrdinalValue(){
        return gillSize.ordinal();
    }

    public void setGillSize(GillSize gillSize) {
        this.gillSize = gillSize;
    }

    public GillColor getGillColor() {
        return gillColor;
    }

    public Integer getGillColorOrdinalValue(){
        return gillColor.ordinal();
    }

    public void setGillColor(GillColor gillColor) {
        this.gillColor = gillColor;
    }

    public StalkShape getStalkShape() {
        return stalkShape;
    }

    public Integer getStalkShapeOrdinalValue(){
        return stalkShape.ordinal();
    }

    public void setStalkShape(StalkShape stalkShape) {
        this.stalkShape = stalkShape;
    }

    public StalkRoot getStalkRoot() {
        return stalkRoot;
    }

    public Integer getStalkRootOrdinalValue(){
        return stalkRoot.ordinal();
    }

    public void setStalkRoot(StalkRoot stalkRoot) {
        this.stalkRoot = stalkRoot;
    }

    public StalkSurfaceAboveRing getStalkSurfaceAboveRing() {
        return stalkSurfaceAboveRing;
    }

    public Integer getStalkSurfaceAboveRingOrdinalValue(){
        return stalkSurfaceAboveRing.ordinal();
    }

    public void setStalkSurfaceAboveRing(StalkSurfaceAboveRing stalkSurfaceAboveRing) {
        this.stalkSurfaceAboveRing = stalkSurfaceAboveRing;
    }

    public StalkSurfaceBelowRing getStalkSurfaceBelowRing() {
        return stalkSurfaceBelowRing;
    }

    public Integer getStalkSurfaceBelowOrdinalValue(){
        return stalkSurfaceBelowRing.ordinal();
    }

    public void setStalkSurfaceBelowRing(StalkSurfaceBelowRing stalkSurfaceBelowRing) {
        this.stalkSurfaceBelowRing = stalkSurfaceBelowRing;
    }

    public StalkColorAboveRing getStalkColorAboveRing() {
        return stalkColorAboveRing;
    }

    public Integer getStalkColorAboveRingOrdinalValue(){
        return stalkColorAboveRing.ordinal();
    }

    public void setStalkColorAboveRing(StalkColorAboveRing stalkColorAboveRing) {
        this.stalkColorAboveRing = stalkColorAboveRing;
    }

    public StalkColorBelowRing getStalkColorBelowRing() {
        return stalkColorBelowRing;
    }

    public Integer getStalkColorBelowRingOrdinalValue(){
        return stalkColorBelowRing.ordinal();
    }

    public void setStalkColorBelowRing(StalkColorBelowRing stalkColorBelowRing) {
        this.stalkColorBelowRing = stalkColorBelowRing;
    }

    public VeilType getVeilType() {
        return veilType;
    }

    public Integer getVeilTypeOrdinalValue(){
        return veilType.ordinal();
    }

    public void setVeilType(VeilType veilType) {
        this.veilType = veilType;
    }

    public VeilColor getVeilColor() {
        return veilColor;
    }

    public Integer getVeilColorOrdinalValue(){
        return veilColor.ordinal();
    }

    public void setVeilColor(VeilColor veilColor) {
        this.veilColor = veilColor;
    }

    public RingNumber getRingNumber() {
        return ringNumber;
    }

    public Integer getRingNumberOrdinalValue(){
        return ringNumber.ordinal();
    }

    public void setRingNumber(RingNumber ringNumber) {
        this.ringNumber = ringNumber;
    }

    public RingType getRingType() {
        return ringType;
    }

    public Integer getRingTypeOrdinalValue(){
        return ringType.ordinal();
    }

    public void setRingType(RingType ringType) {
        this.ringType = ringType;
    }

    public SporePrintColor getSporePrintColor() {
        return sporePrintColor;
    }

    public Integer getSporePrintColorOrdinalValue(){
        return sporePrintColor.ordinal();
    }

    public void setSporePrintColor(SporePrintColor sporePrintColor) {
        this.sporePrintColor = sporePrintColor;
    }

    public Population getPopulation() {
        return population;
    }

    public Integer getPopulationOrdinalValue(){
        return population.ordinal();
    }

    public void setPopulation(Population population) {
        this.population = population;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public Integer getHabitatOrdinalValue(){
        return habitat.ordinal();
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    //min-max normalization
    public Double normaliseData(Integer value, Integer min, Integer max, Double new_min, Double new_max){
        return ((value/1.0 - min/1.0)/(max/1.0 - min/1.0))*(new_max - new_min) + new_min;
    }

}

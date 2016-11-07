package Data.mgr;

public class poi {
	String title ;
	String photo;
    double lat;
    double lng;
    int elevation;
    int id;
public poi(){
	
}

public poi( int id,String title,double lat,double lng,int elevation,String photo)
{
	this.id=id;
	this.lat=lat;
	this.lng=lng;
	this.elevation=elevation;
	this.title=title;
	this.photo=photo;
}

public int getID()
{
	return this.id;
}
public void setID(int id){
	this.id=id;
}
public String getTitle(){
	return this.title;
}
public void setTitle(String title){
	this.title = title;
}
public String getPhoto(){
	return this.photo;
}
public void setPhoto(String photo){
	this.photo=photo;
}
public int getElevation()
{
	return this.elevation;
}
public void setElevation(int elevation){
	this.elevation=elevation;
}
public double getLat(){
	return this.lat;
}
public void setLat(double lat){
	this.lat=lat;
}
public double getLng(){
	return this.lng;
}
public void setLng(double lng){
	this.lng=lng;
}

}

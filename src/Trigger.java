public class Trigger{
	
	private boolean ativo;
	private double tensao;
	private Canal ch;
	
	public void Trigger(boolean ativo){
		this.ativo = ativo;
	}
	public void config(double tensao, Canal ch){
		this.tensao = tensao;
		this.ch = ch;
	}
	public double getTensao(){
		return tensao;
	}
	public void select(boolean ativo){
		this.ativo = ativo;
	}
	public boolean isEnable(){
		return ativo;
	}
}
var loginIsErro=0;
function isLoad(){
	if(loginIsErro==1){
		$("#loginIsErro").css("display","block");
		//loginIsErro=0;
	}else{
		$("#loginIsErro").css("display","none");
	}
}
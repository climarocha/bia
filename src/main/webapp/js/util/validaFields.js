function isFieldEmpty(field){
	if(field==null || field==undefined || field=="" || field.val()==null || field.val()==undefined || field.val()==""){
		return true;
	}
	return false;
}
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form action="<%=request.getContextPath()%>/str/store.do" method="post" style="margin:0;">
	<div class="modal fade" id="modal-id">	
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h6 class="modal-title">是否確定登出</h6>
				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-sm btn-success" value="確定" style="width:auto;">
					<input type="hidden" name="action" value="str_logout">
				</div>
			</div>
		</div>
	</div>
</form>
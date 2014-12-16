<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
<title> Welcome </title>

<%@ include file="./inc_theme.jsp"%>

</head>
<body> 
	<section class="vbox">

		<section>
			<section class="hbox stretch">

				<section id="content">
					<section class="vbox">
						<section class="scrollable padder">
							<div class="alert alert-block alert-error fade in">
				            	<h4 class="alert-heading">รายละเอียดยี่ห้อรถจักรยานยนต์</h4>
				          	</div>
							<div class="row">
								<div class="col-sm-12">
									<section class="panel panel-default">
										<header class="panel-heading font-bold">รายละเอียดใบกำกับภาษี</header>
										<div class="panel-body">
												<div class="form-group">
													<label>เลขที่ใบกำกับ :</label> 
													<input type="text" id="noBill" name="noBill" disabled>
													<label>วันที่บันทึก:</label> 
													<input type="text" id="dateAdd" name="dateAdd" disabled>
												</div>
												<div class="form-group">
													<label>ยี่ห้อ :</label> 
													<input type="text" id="noBill" name="noBill">
													<label>รุ่น:</label> 
													<input type="text" id="dateAdd" name="dateAdd">
												</div>
												<button class="btn btn-sm btn-default">button1</button>
												<button class="btn btn-info">button2</button>
												<button class="btn btn-success">button3</button>
												<button class="btn btn-danger">button4</button>
												<button class="btn btn-primary">button5</button>
										</div>
									</section>
								</div>
							</div>
							
							<section class="panel panel-default">
								<header class="panel-heading font-bold"> Form elements
								</header>
								<div class="panel-body">
									<form class="form-horizontal" method="get">
										<div class="form-group">
											<label class="col-sm-2 control-label">Checkboxes and
												radios</label>
											<div class="col-sm-10">
												<div class="checkbox">
													<label> <input type="checkbox" value="">
														Option one is this and that&mdash;be sure to include why
														it's great
													</label>
												</div>

												<div class="radio">
													<label> <input type="radio" name="optionsRadios"
														id="optionsRadios1" value="option1" checked>
														Option one is this and that&mdash;be sure to include why
														it's great
													</label>
												</div>
												<div class="radio">
													<label> <input type="radio" name="optionsRadios"
														id="optionsRadios2" value="option2"> Option two
														can be something else and selecting it will deselect
														option one
													</label>
												</div>
											</div>
										</div>
										<div class="line line-dashed line-lg pull-in"></div>
										<div class="form-group">
											<label class="col-sm-2 control-label">Inline
												checkboxes</label>
											<div class="col-sm-10">
												<label class="checkbox-inline"> <input
													type="checkbox" id="inlineCheckbox1" value="option1">
													a
												</label> <label class="checkbox-inline"> <input
													type="checkbox" id="inlineCheckbox2" value="option2">
													b
												</label> <label class="checkbox-inline"> <input
													type="checkbox" id="inlineCheckbox3" value="option3">
													c
												</label>
											</div>
										</div>
										<div class="line line-dashed line-lg pull-in"></div>
										<div class="form-group">
											<label class="col-sm-2 control-label">Control sizing</label>
											<div class="col-sm-10">
												<input class="form-control input-lg m-b" type="text"
													placeholder=".input-lg"> <input
													class="form-control m-b" type="text"
													placeholder="Default input"> <input
													class="form-control input-sm" type="text"
													placeholder=".input-sm">
											</div>
										</div>
										<div class="line line-dashed line-lg pull-in"></div>
										<div class="form-group">
											<label class="col-sm-2 control-label">Column sizing</label>
											<div class="col-sm-10">
												<div class="row">
													<div class="col-md-2">
														<input type="text" class="form-control"
															placeholder=".col-md-2">
													</div>
													<div class="col-md-3">
														<input type="text" class="form-control"
															placeholder=".col-md-3">
													</div>
													<div class="col-md-4">
														<input type="text" class="form-control"
															placeholder=".col-md-4">
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">Custom Radio
												and Checkbox (Retina display)</label>
											<div class="col-sm-10">
												<div class="row">
													<div class="col-sm-6">
														<!-- radio -->
														<div class="radio">
															<label class="radio-custom"> <input type="radio"
																name="radio" checked="checked"> <i
																class="fa fa-circle-o"></i> Item one checked
															</label>
														</div>
														<div class="radio">
															<label class="radio-custom"> <input type="radio"
																name="radio"> <i class="fa fa-circle-o"></i>
																Item two
															</label>
														</div>
														<div class="radio">
															<label class="radio-custom"> <input type="radio"
																name="radio" disabled="disabled"> <i
																class="fa fa-circle-o"></i> Item three disabled
															</label>
														</div>
														<div class="radio">
															<label class="radio-custom"> <input type="radio"
																checked="checked" disabled="disabled"> <i
																class="fa fa-circle-o"></i> Item four checked disabled
															</label>
														</div>
													</div>
													<div class="col-sm-6">
														<!-- checkbox -->
														<div class="checkbox">
															<label class="checkbox-custom"> <input
																type="checkbox" name="checkboxA" checked="checked">
																<i class="fa fa-fw fa-square-o"></i> Item one checked
															</label>
														</div>
														<div class="checkbox">
															<label class="checkbox-custom"> <input
																type="checkbox" name="checkboxB" id="2"> <i
																class="fa fa-fw fa-square-o"></i> Item two
															</label>
														</div>
														<div class="checkbox">
															<label class="checkbox-custom"> <input
																type="checkbox" name="checkboxC" disabled="disabled">
																<i class="fa fa-fw fa-square-o"></i> Item three disabled
															</label>
														</div>
														<div class="checkbox">
															<label class="checkbox-custom"> <input
																type="checkbox" name="checkboxD" checked="checked"
																disabled="disabled"> <i
																class="fa fa-fw fa-square-o"></i> Item four checked
																disabled
															</label>
														</div>
													</div>
												</div>
											</div>
										</div>

										<div class="line line-dashed line-lg pull-in"></div>
										<div class="form-group">
											<label class="col-sm-2 control-label">Select</label>
											<div class="col-sm-10">
												<div class="m-b">
													<select id="select2-option" style="width: 260px">
														<optgroup label="Alaskan/Hawaiian Time Zone">
															<option value="AK">Alaska</option>
															<option value="HI">Hawaii</option>
														</optgroup>
														<optgroup label="Pacific Time Zone">
															<option value="CA">California</option>
															<option value="NV">Nevada</option>
															<option value="OR">Oregon</option>
															<option value="WA">Washington</option>
														</optgroup>
														<optgroup label="Mountain Time Zone">
															<option value="AZ">Arizona</option>
															<option value="CO">Colorado</option>
															<option value="ID">Idaho</option>
															<option value="MT">Montana</option>
															<option value="NE">Nebraska</option>
															<option value="NM">New Mexico</option>
															<option value="ND">North Dakota</option>
															<option value="UT">Utah</option>
															<option value="WY">Wyoming</option>
														</optgroup>
														<optgroup label="Central Time Zone">
															<option value="AL">Alabama</option>
															<option value="AR">Arkansas</option>
															<option value="IL">Illinois</option>
															<option value="IA">Iowa</option>
															<option value="KS">Kansas</option>
															<option value="KY">Kentucky</option>
															<option value="LA">Louisiana</option>
															<option value="MN">Minnesota</option>
															<option value="MS">Mississippi</option>
															<option value="MO">Missouri</option>
															<option value="OK">Oklahoma</option>
															<option value="SD">South Dakota</option>
															<option value="TX">Texas</option>
															<option value="TN">Tennessee</option>
															<option value="WI">Wisconsin</option>
														</optgroup>
														<optgroup label="Eastern Time Zone">
															<option value="CT">Connecticut</option>
															<option value="DE">Delaware</option>
															<option value="FL">Florida</option>
															<option value="GA">Georgia</option>
															<option value="IN">Indiana</option>
															<option value="ME">Maine</option>
															<option value="MD">Maryland</option>
															<option value="MA">Massachusetts</option>
															<option value="MI">Michigan</option>
															<option value="NH">New Hampshire</option>
															<option value="NJ">New Jersey</option>
															<option value="NY">New York</option>
															<option value="NC">North Carolina</option>
															<option value="OH">Ohio</option>
															<option value="PA">Pennsylvania</option>
															<option value="RI">Rhode Island</option>
															<option value="SC">South Carolina</option>
															<option value="VT">Vermont</option>
															<option value="VA">Virginia</option>
															<option value="WV">West Virginia</option>
														</optgroup>
													</select>
												</div>
												<div>
													<input type="hidden" id="select2-tags" style="width: 260px"
														value="brown" />
												</div>
											</div>
										</div>									
									</form>
								</div>
				              <div class="row">
								<div class="col-sm-12">
									<section class="panel panel-default">
					                	<table id="tbl_search" border="0" align="center"  class="table span12"  style=" width:400px;" >
											<tr>
												<td>
													<table id="tbl_result" border="1" class="table span12"  style="border: 2; width: 370px;">
														<tr bgcolor="#473636"  class="text_white" >
															<th  style="text-align: center;"><B>ลำดับ</B></th>
															<th  style="text-align: center;" ><B>ยี่ห้อรถจักรยานยนต์</B></th>
															<th  style="text-align: center;" ><B>add/delete</B></th>
														</tr>
														<colgroup>
															<col width="50px;" align="center" />
															<col width="250px;" align="left" />
															<col width="50px;" align="center" /> 
														</colgroup>
														<tr>
															<td align="center" >
															   <label><B>1</B></label>
															</td>
															<td align="left">
															   <input type="text" name="xxx" id="xxx" size="70" value="Honda">
															</td>
															<td align="center">
															  <button id="btn_delete" name="btn_delete"  class="btn btn-warning btn-mini fa fa-times" style="width:25px;" onclick="lp_del_row_table();"></button>
															</td>
														</tr>
														<tr>
															<td align="center">
															   <label><B>2</B></label>
															</td>
															<td align="left">
															   <input type="text" name="xxx" id="xxx" size="70" value="Kawasaki">
															</td>
															<td align="center">
															  <button id="btn_delete" name="btn_delete"  class="btn btn-warning btn-mini fa fa-times" style="width:25px;" onclick="lp_del_row_table();"></button>
															</td>
														</tr>
														<tr>
															<td align="center">
															   <label><B>3</B></label>
															</td>
															<td align="left">
															   <input type="text" name="xxx" id="xxx" size="70" value="Suzuki">
															</td>
															<td align="center">
															  <button id="btn_delete" name="btn_delete"  class="btn btn-warning btn-mini fa fa-times" style="width:25px;" onclick="lp_del_row_table();"></button>
															</td>
														</tr>
														<tr>
															<td align="center">
															   <label><B>4</B></label>
															</td>
															<td align="left">
															   <input type="text" name="xxx" id="xxx" size="70" value="Vespa">
															</td>
															<td align="center">
															  <button id="btn_delete" name="btn_delete"  class="btn btn-warning btn-mini fa fa-times" style="width:25px;" onclick="lp_del_row_table();"></button>
															</td>
														</tr>
														<tr>
															<td align="center">
															   <label><B>5</B></label>
															</td>
															<td align="left">
															   <input type="text" name="xxx" id="xxx" size="70" value="Yamaha">
															</td>
															<td align="center">
															  <button id="btn_delete" name="btn_delete"  class="btn btn-warning btn-mini fa fa-times" style="width:25px;" onclick="lp_del_row_table();"></button>
															</td>
														</tr>
														<tr>
															<td align="center" style="visibility:hidden;">
															   <label><B>1</B></label>
															</td>
															<td align="left" style="visibility:hidden;">
															   <input type="text" name="xxx"  id="xxx" size="70">
															</td>
															<td align="center">
															  <button id="btn_add"    name="btn_add"    class="btn btn-warning btn-mini fa fa-plus-square"  style="width:25px;" onclick="lp_add_row_table()"></button>
															</td>
														</tr>
													</table> 
												</td>
											</tr>
										</table>
										</section>
										</div>
									</div>
				              </section>
							</section>
						</section>
					</section>
					<a href="#" class="hide nav-off-screen-block"
						data-toggle="class:nav-off-screen" data-target="#nav"></a>
				</section>

			</section>
		</section>
</body>
</html>

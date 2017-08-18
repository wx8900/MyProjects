<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
			<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

				<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


				<html xmlns="http://www.w3.org/1999/xhtml">
					<head>
						<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
							<title>University Enrollments</title>

							<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>
						</head>

						<body>
							<table role="main" width="700" border="0" cellspacing="0" cellpadding="0" align="center" style="margin-top:1em">
								<tbody>
									<tr>
										<td>
											<h1>${greeting}</h1>
										</td>
									</tr>
									<tr>
										<td>
											<form action="searchjobs" method="get" name="jobsearch" id="jobsearch" onsubmit="formptk('form','where_ac','',['where_ac','what_ac'], ptk);formptk('form','what_ac','w',['where_ac','what_ac'], ptk);">
												<span id="hidden_colon" style="display:none;">:</span>
												<table cellspacing="0" cellpadding="3" width="100%" align="center">
													<tbody>
														<tr>
															<td class="npb">
																<label for="what" id="what_label_top" aria-hidden="true">what</label>
															</td>
															<td class="npb" colspan="2">
																<label for="where" id="where_label_top" aria-hidden="true">where</label>
															</td>
														</tr>

														<tr>
															<td class="npl epr">
																<span class="inwrap">
																	<input class="input_text" maxlength="512" size="31" aria-labelledby="what_label_top hidden_colon what_label_bot" name="q" autocomplete="off" id="what">
																	</span>
																	<div style="width:250px">
																	</div>
																</td>
																<td class="npl epr">
																	<span class="inwrap">
																		<input class="input_text" maxlength="64" size="27" aria-labelledby="where_label_top hidden_colon where_label_bot" name="l" autocomplete="off" id="where">
																		</span>
																		<div style="width:200px">
																		</div>
																	</td>
																	<td class="npl" style="width:1px">
																		<span class="inwrapBorder" style="width:auto;">
																			<span class="inwrapBorderTop">
																				<input id="fj" type="submit" class="input_submit" value="Search Jobs">
																				</span>
																			</span>
																		</td>
																	</tr>
																	<tr id="acr">
																		<td>
																			<span class="h">&nbsp;</span>
																		</td>
																		<td colspan="2" class="npl">
																			<div style="position:relative;z-index:2">
																				<div id="acdiv" class="acd" style="display:none;">
																				</div>
																			</div>
																		</td>
																	</tr>
																	<tr id="acr">
																		<td colspan="3" class="npl">
																			<div style="position:relative;z-index:2">
																				<div id="what_acdiv" class="acd" style="display:none;">
																				</div>
																			</div>
																		</td>
																	</tr>
																	<tr valign="baseline">
																		<td class="label">
																			<label for="what" id="what_label_bot" aria-hidden="true">job title, keywords or company name</label>
																		</td>
																		<td class="label">
																			<label for="where" id="where_label_bot" aria-hidden="true">city, state or zip code</label>
																		</td>
																		<td style="padding-top:0">
																			<a class="sl" href="/advanced_search?l=Thousand+Oaks%2C+CA">Advanced Job Search</a>
																		</td>
																	</tr>
																</tbody>
															</table>
														</form>
														<script type="text/javascript">document.forms.jobsearch.q.focus();</script>
														<br>


															<div class="icon-promo-container resPad">
																<div class="icon-promo-container-inner ">
																	<p id="resPromoDisplay" class="resume-promo">
																		<a tabindex="-1" aria-hidden="true" href="/promo/resume" onclick="this.href = appendParamsOnce( this.href, '?from=hp2&amp;subfrom=rezprmstd&amp;trk.origin=homepage&amp;trk.variant=rezprmstd&amp;trk.tk=1bbhrl1nfbr0h9lb')">
																			<span aria-hidden="true" role="img" class="new-ico">
																			</span>
																		</a>
																		<a href="/promo/resume" onclick="this.href = appendParamsOnce( this.href, '?from=hp2&amp;subfrom=rezprmstd&amp;trk.origin=homepage&amp;trk.variant=rezprmstd&amp;trk.tk=1bbhrl1nfbr0h9lb')" class="resume-promo-link">
																			<b>Upload your resume</b>
																		</a> - It only takes a few seconds</p>

																</div>
															</div>
															<script type="text/javascript">
function jsall_loaded() {


initProcessLeftoverDwellEntries();

    detectBrowserState('homepage', '1bbhrl1nfbr0h9lb');

window.tk = "1bbhrl1nfbr0h9lb";
initAutocomplete('where_ac', gbid('where'), gbid('acdiv'), '/rpc/suggest?from=hp&tk=1bbhrl1nfbr0h9lb', function () {
formptk('form', 'where_ac', '', ['where_ac','what_ac'], ptk);
}, gbid('where'));

initAutocomplete('what_ac', gbid('what'), gbid('what_acdiv'), '/rpc/suggest?what=true&from=hp&tk=1bbhrl1nfbr0h9lb', function () {
formptk('form', 'what_ac', 'w', ['where_ac','what_ac'], ptk)
}, gbid('what'));

}
</script>

															<script type="text/javascript">
    
    window['closureReadyCallbacks'] = [];

    function call_when_jsall_loaded(cb) {
        if (window['closureReady']) {
            cb();
        } else {
            window['closureReadyCallbacks'].push(cb);
        }
    }
</script>

															<script type="text/javascript">
function loadJSAsync( ) {
for ( var i = 0; i < arguments.length; i++ ) {
var url = arguments[i];
(function() {
var s = document.createElement("script"), el = document.getElementsByTagName("script")[0];
s.async = true;
s.src = url;
el.parentNode.insertBefore(s, el);
})();
}
}
</script>
																	<script type="text/javascript">
                    loadJSAsync('/s/fb87d52/jobsearch-all-compiled.js')
                </script>
																	<script type="text/javascript">
function rpc(url, setd) { if (document.images) (new Image()).src=url+'&zr='+rand(); }
function rand() { return Math.floor(Math.random()*2147483647); }
</script>
																	<div style="text-align:center;">
																		<div id="recPromoDisplay" style="display:none;">
																		</div>

																		<script type="text/javascript">
        call_when_jsall_loaded(function() {
            var recJobLink = new RecJobLink("Recommended Jobs", "recPromoDisplay", "1bbhoc6eebr0h8fi", "1bbhrl1nfbr0h9lb",
                    "US", "en", "",
                    "", null, true);
            recJobLink.onLoad();
        });
    </script>

																	</div>
																	<div class="srchlst_wrap">
																		<div class="srchlst">
																			<div class="rsh">My recent searches -
                        <a class="sl" href="/?cls=1&amp;hct=8f947dd576b89cebd44bed1e6e4f71ab">clear</a>
																			</div>
																			<div class="rsi">
																				<a id="rsl_0" href="/jobs?q=PHP&amp;l=Thousand+Oaks%2C+CA&amp;rq=1" onmousedown="ptk('','rq=1');" title="PHP - Thousand Oaks, CA">PHP - Thousand Oaks, CA</a>
																			</div>
																		</div>
																	</div>
																	<div id="gajui">
																		<span class="gaj_heading">Indeed helps people get jobs:</span>
																		<a href="/promo/gotajob" onmousedown="this.href = appendParamsOnce( this.href, '?trk.origin=homepage&amp;trk.tk=1bbhrl1nfbr0h9lb')" class="sl">Over 10 million stories shared</a>
																	</div>
																</td>
															</tr>
														</tbody>
													</table>
												</body>
											</html>
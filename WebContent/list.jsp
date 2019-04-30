<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ page import="uploadfile.upload.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>least.js - Random &amp; Responsive HTML 5, CSS3 Gallery with LazyLoad</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="css/style.min.css" rel="stylesheet" />
        <link href="css/prism.min.css" rel="stylesheet" />
    </head>
    <body> 
        <!-- Gallery -->
        <section>
            <ul id="gallery">
                <li id="fullPreview"></li>
                <!-- 1 -->
                <%
				// 获取新闻信息集合
					List<Picture> list = (List<Picture>)request.getAttribute("list");
					// 判断集合是否有效
					if(list == null || list.size() < 1){
						out.print("没有数据！");
					}else{
						// 遍历新闻集合中的数据
						for(Picture pics : list){
			%>
                <li>
                    <a href="<%=pics.getFiler_url() %>"></a>
                    <img data-original="<%=pics.getFiler_url() %>" src="img/effects/white.gif" width="240" height="150" alt="Ocean" />
                    
                    <div class="overLayer"></div>
                    <div class="infoLayer">
                        <ul>
                            <li>
                                <h2>
                                    <%=pics.getUsername() %>   
                                </h2>
                            </li>
                            <li>
                                <p>
                                                                                                                                浏览图片
                                </p>
                            </li>
                        </ul>
                    </div>
                    <div class="projectInfo">
                        <strong>简介</strong> <%=pics.getDemo() %>
                    </div>
                </li>
                <%
					}
				}
			%>
            </ul>
        </section>
        <!-- / Gallery -->
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.lazyload.js"></script>
        <script src="js/least.min.js"></script>
        <script src="js/prism.js"></script>
    </body>
</html>
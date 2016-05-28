<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <ul class="language">
        <li>
            <form action="changeLanguage" method="post">
                <input type="hidden" name="loc" value="ru_RU"/>
                <a href="javascript:;" onclick="parentNode.submit()">
                    <img class="img-responsive img-header-lang" src="images/rus.png" alt="Russian language">
                </a>
            </form>
        </li>
        <li>
            <form action="changeLanguage" method="post">
                <input type="hidden" name="loc" value="en_EN"/>
                <a href="javascript:;" onclick="parentNode.submit()">
                    <img class="img-responsive img-header-lang" src="images/eng.png" alt="English language">
                </a>
            </form>
        </li>
    </ul>
<div style="clear: right"></div>

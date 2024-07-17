<%-- 
    Document   : Calendar1
    Created on : Mar 12, 2024, 2:40:47 PM
    Author     : kienm
--%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>DATE CHOOOSE</title>
<!--    <link rel="stylesheet" href="./css/calendar.css" /> -->
  </head>
  
  <body>
      
      <div class="datepicker-container">
      <input type="text" class="date-input" placeholder="Select date" />

      <div class="datepicker" hidden>
        <!-- .datepicker-header -->
        <div class="datepicker-header">
          <button class="prev">Prev</button>

          <div>
            <select class="month-input">
              <option>January</option>
              <option>February</option>
              <option>March</option>
              <option>April</option>
              <option>May</option>
              <option>June</option>
              <option>July</option>
              <option>August</option>
              <option>September</option>
              <option>October</option>
              <option>November</option>
              <option>December</option>
            </select>
            <input type="number" class="year-input" />
          </div>

          <button class="next">Next</button>
        </div>
        <!-- /.datepicker-header -->

        <!-- .days -->
        <div class="days">
          <span>Sun</span>
          <span>Mon</span>
          <span>Tue</span>
          <span>Wed</span>
          <span>Thu</span>
          <span>Fri</span>
          <span>Sat</span>
        </div>

        <div class="dates"></div>
        
        <p></p>
        
            <div class="datepicker-footer"> 
                <input type="button" class="cancel" value="Cancel" style="width: 70px;
                                                                          height: 35px ">
                <form style="width: 150px;
                             height: 35px;
                             margin-bottom: 0;
                             padding-bottom: 0;
                             padding-top: 0;">
                        <input type="hidden" value ="">
                        <input type="button" class="apply" onclick="loadDoc()" name="action" value="search" id="action" style=" width: 100%;
                                                                                                                                height: 100%;">     
                    </form>
            </div>
            
            <div id="demo" style="visibility: hidden">
                
            </div>

      </div>
    </div>
      
      <script type="text/javascript" >
         
        const datepicker = document.querySelector(".datepicker");
        const dateInput = document.querySelector(".date-input");
        const yearInput = datepicker.querySelector(".year-input");
        const monthInput = datepicker.querySelector(".month-input");
        const cancelBtn = datepicker.querySelector(".cancel");
        const applyBtn = datepicker.querySelector(".apply");
        const nextBtn = datepicker.querySelector(".next");
        const prevBtn = datepicker.querySelector(".prev");
        const dates = datepicker.querySelector(".dates");
        
        let selectedDate = new Date();
        let year = selectedDate.getFullYear();
        let month = selectedDate.getMonth();

        dateInput.addEventListener("click", () => {
            datepicker.hidden = false;
        });

        cancelBtn.addEventListener("click", () => {
            datepicker.hidden = true;
        });

        nextBtn.addEventListener("click", () => {
            if (month === 11) year++;
                month = (month + 1) % 12;
                displayDates();
        });
 
        prevBtn.addEventListener("click", () => {
            if (month === 0) year--;
                month = (month - 1 + 12) % 12;
                displayDates();
        });

        monthInput.addEventListener("change", () => {
            month = monthInput.selectedIndex;
            displayDates();
        });

        yearInput.addEventListener("change", () => {
            year = yearInput.value;
            displayDates();
        });

        const updateYearMonth = () => {
            monthInput.selectedIndex = month;
            yearInput.value = year;
        };

        const handleDateClick = (e) => {
            const button = e.target;
            const selected = dates.querySelector(".selected");
            selected && selected.classList.remove("selected");

            button.classList.add("selected");
            
            selectedDate = new Date(year, month, parseInt(button.textContent));
            
        };

        const displayDates = () => {
            updateYearMonth();

            dates.innerHTML = "";

            const lastOfPrevMonth = new Date(year, month, 0);

            for (let i = 0; i <= lastOfPrevMonth.getDay(); i++) {
                const text = lastOfPrevMonth.getDate() - lastOfPrevMonth.getDay() + i;
                const button = createButton(text, true, -1);
                dates.appendChild(button);
            }

            const lastOfMOnth = new Date(year, month + 1, 0);

            for (let i = 1; i <= lastOfMOnth.getDate(); i++) {
                const button = createButton(i, false);
                button.addEventListener("click", handleDateClick);
                dates.appendChild(button);
            }
            
            const firstOfNextMonth = new Date(year, month + 1, 1);

            for (let i = firstOfNextMonth.getDay(); i < 7; i++) {
                const text = firstOfNextMonth.getDate() - firstOfNextMonth.getDay() + i;
                const button = createButton(text, true, 1);
                dates.appendChild(button);
            }
        };

        const createButton = (text, isDisabled = false, type = 0) => {
            const currentDate = new Date();

            let comparisonDate = new Date(year, month + type, text);

            const isToday =
                currentDate.getDate() === text &&
                currentDate.getFullYear() === year &&
                currentDate.getMonth() === month;

            const selected = selectedDate.getTime() === comparisonDate.getTime();

            const button = document.createElement("button");
            button.textContent = text;
            button.disabled = isDisabled;
            button.classList.toggle("today", isToday);
            button.classList.toggle("selected", selected);
            return button;
        };

        displayDates();
    </script>
    
    <script>
        
        function loadDoc() {
            
            applyBtn.addEventListener("click", () => {
            dateInput.value = selectedDate.toLocaleDateString("fr-CA", {
                year: "numeric",
                month: "2-digit",
                day: "2-digit",
            });
            datepicker.hidden = true; 
            const dt = dateInput.value;
            
            var x = dt.toString();
            
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange=function() {
                if (this.readyState === 4 && this.status === 200) {
                    document.getElementById("demo").innerHTML = this.responseText;
                }
            };
            
            xhttp.open("POST", "statistic?action=search&datename=" + x, true);
            location.href = 'statistic?action=search&datename=' + x;
            xhttp.send();
    });
            
            
}
    </script>

     
  </body>
</html>


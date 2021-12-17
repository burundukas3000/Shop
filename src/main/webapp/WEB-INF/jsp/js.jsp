
<head>
    <script type="text/javascript">
        function updateQnt(clickedId) {
            event.preventDefault();
            if(clickedId == "minus"){
                let productId = $(this).attr("pid");
                let qnt = document.getElementById('qnt'+productId);
                console.log(qnt.value);
                qnt.value = parseInt(qnt.value)-1;
                console.log(qnt.value)
                alert('minus');
            }
            else{
                let qnt = document.getElementById('qnt');
                qnt.value = parseInt(qnt.value)+1;
                alert('plus');
            }

        }

        function handleClick(clickedId)
        {
            if(clickedId == "customerId"){
                document.getElementById('tableTextId');
                alert('first');
            }
            else{
                document.getElementById('tableTextId');
                alert('second');
            }
        }
    </script>
    <title>JavaScript methods for Shopping Cart</title>
</head>
<body></body>
</html>
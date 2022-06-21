$(document).ready(function () {

    $(document).on('click',  '.btn-add-to-cart', function () {

        let url = '/cart/addItem?itemId=' + $(this).attr('item-id') + '&shopId=' + $(this).attr('shop-id') +
            '&quantity=' + $(this).attr('data-quantity');

        let xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "POST", url, true );
        xmlHttp.send( null );


        // let jsonForResponse = {
        //     shopId: $(this).attr('shop-id'),
        //     itemId: $(this).attr('item-id'),
        //     quantity: $(this).attr('data-quantity')
        // };
        //
        // $.ajax({
        //     headers: {
        //         'Accept': 'application/json',
        //         'Content-Type': 'application/json'
        //     },
        //     url: '/cart/addItem',
        //     type: "post",
        //     datatype: "json",
        //     data: JSON.stringify(jsonForResponse)
        // })
    })

})
// gets a reference to the heartDOm
const cartsDOM = document.querySelectorAll('.remove-button_cart');
let table = $('#tableFon');

// create a onclick listener
cartsDOM.forEach(e => e.onclick = async (event) => {

    // this is what we clicked on
    const target = event.currentTarget;

    let data;
    data = await fetchData('/cart/deleteItem?cartItemId=', target.dataset.itemId, 'DELETE', 'itemId');

    // check if liked
    if (target.classList.contains('far')) {
        if (target.getAttribute('id') === 'shop-heart') {
            // data = await fetchData('/cart/shop/add/', target.dataset.shopId, 'POST', 'shopId');
        } else {
            data = await fetchData('/cart/addItem/', target.dataset.itemId, 'POST', 'itemId');
        }
        if (data.success) {
            // remove empty heart if liked and add the full heart
            target.classList.remove('far');
            target.classList.add('fas');
        } else {
            console.log('Error retrieving data from server')
        }
    } else {
        if (target.getAttribute('id') === 'shop-heart') {
            // data = await fetchData('/favorite/shop/remove/', target.dataset.shopId, 'DELETE', 'shopId');
        } else {
        }
        if (data.success) {
            // remove full heart if unliked and add empty heart
            target.classList.remove('fas');
            target.classList.add('far');
        }
    }
});

async function fetchData(url, elementId, method, type) {
    const response = await fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        body: type + '=' + elementId,
        // body: JSON.stringify({ [type]: elementId })
    });
    if (!response.ok) {
        throw new Error("cannot fetch data");
    }
    return await response.json();
}
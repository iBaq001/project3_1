// gets a reference to the heartDOm
const heartsDOM = document.querySelectorAll('.remove-button');

// create a onclick listener
heartsDOM.forEach(e => e.onclick = async (event) => {

    // this is what we clicked on
    const target = event.currentTarget;

    let data = await fetchData('/favorite/' + target.dataset.type + '/remove/', target.dataset.id, 'DELETE', target.dataset.type + 'Id');

    if (data.success) {
        // remove card
        target.closest('.element-card').remove();
    }else {
            console.log('Error remove operation')
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
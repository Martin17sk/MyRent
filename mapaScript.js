
mapboxgl.accessToken = 'pk.eyJ1Ijoic2FkZGRnZSIsImEiOiJjbHltOWE4ejUxY21lMmtvY2s3ZG5iN2JiIn0.UtJHjn2nuEqAbHkLSOh4pA';

var map = new mapboxgl.Map({
    container: 'map',
    style: 'mapbox://styles/sadddge/cm0wwdbqx00fi01nt5aazaoaz',
    center: [-74.5, 40],
    zoom: 9
});

let marker = null;

function addMarker(e) {
    if (marker) {
        marker.remove();
    }
    const coords = e.lngLat;
    

    let popup = new mapboxgl.Popup({closeButton: false}).setHTML('<h3>Agregar una propiedad</h3>');

    

    marker = new mapboxgl.Marker({
        draggable: true 
        }).setLngLat([coords.lng, coords.lat])
        .setPopup(popup)
        .addTo(map);
    
    popup.addTo(map);


    const popupElement = popup.getElement();
    changeColor('#303036');

    document.getElementsByClassName('mapboxgl-popup-content')[0].style.cursor = 'pointer';
    document.getElementsByClassName('mapboxgl-popup-content')[0].style.borderRadius = '10px';
    document.getElementsByClassName('mapboxgl-popup-content')[0].style.border = '1px solid rgba(255, 255, 255, 0.25)';

    popupElement.addEventListener('mouseover', () => { changeColor('#3f3f44'); });

    popupElement.addEventListener('mouseout', () => {changeColor('#303036');});
}

function changeColor(color) {
    document.getElementsByClassName('mapboxgl-popup-content')[0].style.backgroundColor = color;
    document.getElementsByClassName('mapboxgl-popup-tip')[0].style.backgroundColor = color;
}


map.addControl(new MapboxGeocoder({
    accessToken: mapboxgl.accessToken
}), 'top-left'); 

map.addControl(new mapboxgl.FullscreenControl(), 'bottom-right');
map.addControl(new mapboxgl.NavigationControl(), 'bottom-right');

map.doubleClickZoom.disable();

map.on('dblclick', addMarker);


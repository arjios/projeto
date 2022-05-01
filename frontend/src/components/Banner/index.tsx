import "./style.css";

function Banner() {
  return (
    <div className="banner-container">
      <div className="banner-title">
        <label>MovieFlix</label>
      </div>
      <div className="banner-subtitle">
        <label>
          Diga o que você achou do seu
          <br />
          filme favorito
        </label>
      </div>
      <div className="banner-desenho">
        <img src="assets/images/desenho.png" alt="Desenho"/>
      </div>
    </div>
  );
}

export default Banner;

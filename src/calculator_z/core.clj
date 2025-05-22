(ns calculator-z.core
  (:require [clj-http.client :as client]
            [calculator-z.auth :as auth])
  (:gen-class))

;; gravar as transações no atom
;; usar o swap!

;;https://www.fatsecret.com.br/#fatsecret-platform

;; (def api-url "https://caloriasporalimentoapi.herokuapp.com/api/calorias/")

 
 
;; (defn buscar-alimentos [alimento]
;;   (let [res (client/get api-url
;;                         {:query-params {"descricao" alimento}
;;                         :as :json})]
;;     (:body res)))

;; (defn buscar-calorias [alimento]
;;   (let [res (client/get api-url
;;                         {:query-params {"descricao" alimento}
;;                         :as :json})
;;             dados (:body res)]
;;           (map :calorias dados)))

;; (defn mostrar-calorias []
;;   (let [alimento (read)]
;;   (println (buscar-calorias alimento))))

;; (defn consulta-API []
;;    (let [entrada (read-line)
;;       resultado (buscar-alimentos entrada)]
;;     (run!
;;       #(println (str "\"" (:descricao %) "\" contem " (:calorias %) " calorias por " (:quantidade %)))
;;       resultado)))

;; (defn calcula-calorias []
;;   (let [alimento (read)  quantidade (read) resultado (*(first (buscar-calorias alimento)) quantidade)
;;   ]
;;   (println "Voce consumiu " resultado " calorias")))


;; (defn recebe-token []
;;   (println "Obtendo token de acesso...")
;;   (let [token-info (auth/get-access-token)]
;;     (println "Token recebido:")
;;     (println token-info)))

;; (defn buscar-alimento [descricao]
;;   (let [token (auth/get-access-token)
;;     url "https://platform.fatsecret.com/rest/server.api"
;;     params {:method "food.search"
;;             :search_expressiom descricao
;;             :format "json"}
;;     headers {"Authorization" (str "Bearer " token)}
;;     response (client/get url {:query-params params
;;                               :headers headers
;;                               :as :json})]
;;   (:body response)))

;; (defn alimentos-info []
;;   (let [entrada (read)]
;;   (println (buscar-alimento entrada))))


;; (defn recebe-alimentos []
;;   (let [entrada (read)]
;;     (println (auth/buscar-alimento entrada))))


(defn ganho []
  (let [alimento (read) quantidade (read)]
    (println (auth/calcular-calorias alimento quantidade))))


(defn -main []
  (println "\nMenu")
  (println "Opcao 1 - ganho")
  (println "Opcao 2 - Perda")
  (println "Opcao 3 - Rertorna Calorias de alimento(s)")
  (println "Opcao 4 - Calcula o ganho de calorias")
  (println "Opcao 5 - Sair")
  (def op (read))

  (cond
    (= op 1) (do (ganho) (recur))
    ;; (= op 2) (do (perda) (recur))
    ;; (= op 3) (do (recebe-alimentos) (recur))
    (= op 5) (println "Saindo...")))

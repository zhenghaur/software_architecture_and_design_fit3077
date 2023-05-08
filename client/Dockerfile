FROM node:17-alpine3.12
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY package.json .
COPY package-lock.json .
RUN npm install
COPY . .
EXPOSE 5173
CMD ["npm", "run", "dev"]